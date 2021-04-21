/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.encryption;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * Asynchronously loads and displays a random image.
 *
 * @author Matt Insko
 */
class ImageLoader extends AbstractLoader<ImageView> {


    private final int maxHeight;
    private final int maxWidth;

    ImageLoader(Activity activity, ImageView imageView, int maxHeight, int maxWidth) {
        super(activity, imageView);
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }

    public void execute() {
        new Thread(() -> {
            final Bitmap image = loadImage();
            if (image != null) {
                runOnUIThread(() -> {
                    ImageView view = viewReference.get();
                    if (view != null) {
                        view.setImageBitmap(image);
                    }
                });
            }

        }).start();
    }

    /**
     * Reads and decodes a random image file
     * @return A bitmap of the random image
     */
    private Bitmap loadImage() {
        final long start = System.currentTimeMillis();
        try {
            AssetManager assetManager = activityReference.get().getAssets();
            String[] imageNames = assetManager.list("img");
            if (imageNames == null || imageNames.length == 0) {
                toastOnUIThread("No images found.", true);
                return null;
            }
            final String name = imageNames[rand.nextInt(imageNames.length)];
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = calculateSampleSize(name);
            InputStream is = assetManager.open("img/" + name);
            Bitmap image = getImage(is, options);
            if (image == null) {
                throw new IOException("Image could not be decoded: " + name);
            }
            final long end = System.currentTimeMillis();
            toastOnUIThread(String.format(Locale.ROOT, "Loaded %s in %d ms.", name, (end - start)), false);
            return image;
        } catch (final IOException e) {
            Log.e("ImageLoader", "Error reading images", e);
            toastOnUIThread("Issue loading image: " + e.getMessage(), true);
            return null;
        }
    }

    /**
     * Decodes an image from the stream.
     * @param is The image stream
     * @param options The options for decoding
     * @return The image (or null if there was an issue)
     */
    private Bitmap getImage(InputStream is, BitmapFactory.Options options) {
        return BitmapFactory.decodeStream(is, null, options);
    }

    /**
     * Calculates the sample size based on the maximum height and width.
     * @param imageName The filename of the image
     * @return The sample size to use when decoding the image
     * @throws IOException It one occurs
     */
    private int calculateSampleSize(String imageName) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        getImage(activityReference.get().getAssets().open("img/" + imageName), options);

        /* Loading Large Bitmaps Efficiently
         * https://developer.android.com/topic/performance/graphics/load-bitmap
         */
        final int height = options.outHeight;
        final int width = options.outWidth;
        int sampleSize = 1;
        if (height > maxHeight || width > maxWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / sampleSize) >= maxHeight
                    && (halfWidth / sampleSize) >= maxWidth) {
                sampleSize *= 2;
            }
        }
        return sampleSize;
    }
}
