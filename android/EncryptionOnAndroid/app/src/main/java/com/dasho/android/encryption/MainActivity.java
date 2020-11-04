/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.encryption;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * The main activity for this application.
 *
 * @author Matt Insko
 */
public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private int maxHeight;
    private int maxWidth;
    private enum ViewType {IMAGE, QUOTE}
    private ViewType currentView = ViewType.QUOTE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.ImageBtn).setOnClickListener(ignored -> {
            setupView(ViewType.IMAGE);
            new ImageLoader(this, imageView, maxHeight, maxWidth).execute();
        });
        findViewById(R.id.QuoteBtn).setOnClickListener(ignored -> {
            setupView(ViewType.QUOTE);
            new QuoteLoader(this, textView).execute();
        });
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getCharSequence("quote"));
            Parcelable image = savedInstanceState.getParcelable("image");
            if (image instanceof Bitmap) {
                imageView.setImageBitmap((Bitmap) image);
            }
            currentView = ViewType.values()[savedInstanceState.getInt("viewType")];

        }
        setupView(currentView);
    }

    /**
     * Determines the display dimensions.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        maxHeight = p.y;
        maxWidth = p.x;
    }

    /**
     * Saves the current state
     * @param bundle The bundle
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("quote", textView.getText());
        Drawable d = imageView.getDrawable();
        if (d instanceof BitmapDrawable) {
           bundle.putParcelable("image", ((BitmapDrawable)d).getBitmap());
        }
        bundle.putInt("viewType", currentView.ordinal());
    }

    /**
     * Hides the appropriate type
     * @param type Either a quote or an image
     */
    private void setupView(ViewType type) {
        currentView = type;
        imageView.setVisibility(type == ViewType.IMAGE ? View.VISIBLE : View.INVISIBLE);
        textView.setVisibility(type == ViewType.QUOTE ? View.VISIBLE : View.INVISIBLE);
    }

}
