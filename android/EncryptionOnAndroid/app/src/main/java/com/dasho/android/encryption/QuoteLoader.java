/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.encryption;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Asynchronously loads and displays a random quote.
 *
 * @author Matt Insko
 */
class QuoteLoader extends AbstractLoader<TextView> {

    QuoteLoader(Activity activity, TextView textView) {
        super(activity, textView);
    }

    public void execute() {
        new Thread(() -> {
            String quote = findQuote();
            if (quote != null) {
                runOnUIThread(() -> {
                    TextView view = viewReference.get();
                    if (view != null) {
                        view.setText(quote);
                    }
                });
            }
        }).start();
    }
    /**
     *
     * @return A random quote
     */
    protected String findQuote() {
        try {
            List<String> quotes = readQuotes();
            if (quotes.size() > 0) {
                return quotes.get(rand.nextInt(quotes.size()));
            }
        } catch (final IOException e) {
            Log.e("QuoteLoader", "Error reading quotes", e);
            toastOnUIThread("Issue loading quotes: " + e.getMessage(), true);
        }
        return "\"There are no quotes\" - Matt Insko";
    }

    /**
     * Reads all the quotes and returns them.
     * @return The list of quotes
     * @throws IOException If it occurs
     */
    private List<String> readQuotes() throws IOException {
        InputStream in = activityReference.get().getResources().openRawResource(R.raw.quotes);
        List<String> quotes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line = reader.readLine();
            while (line != null) {
                if (!line.startsWith("#")) {
                    quotes.add(line);
                }
                line = reader.readLine();
            }
        }
        return quotes;
    }
}
