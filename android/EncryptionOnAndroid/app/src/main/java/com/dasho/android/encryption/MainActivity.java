/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.encryption;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
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
    private ScrollView textScroll;
    private ScrollView imageScroll;
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
        textScroll = findViewById(R.id.textScroll);
        imageScroll = findViewById(R.id.imageScroll);
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
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getDisplay().getRealMetrics(displayMetrics);
        } else {
            getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        }
        maxHeight = displayMetrics.heightPixels;
        maxWidth = displayMetrics.widthPixels;
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
        imageScroll.setVisibility(type == ViewType.IMAGE ? View.VISIBLE : View.INVISIBLE);
        imageView.setVisibility(type == ViewType.IMAGE ? View.VISIBLE : View.INVISIBLE);
        textScroll.setVisibility(type == ViewType.QUOTE ? View.VISIBLE : View.INVISIBLE);
        textView.setVisibility(type == ViewType.QUOTE ? View.VISIBLE : View.INVISIBLE);
    }

}
