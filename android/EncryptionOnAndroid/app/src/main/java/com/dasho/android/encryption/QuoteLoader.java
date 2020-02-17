/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.encryption;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Asynchronously loads and displays a random quote.
 *
 * @author Matt Insko
 */
class QuoteLoader extends AsyncTask<Void, Void, String> {
    private final WeakReference<Activity> activityReference;
    private final WeakReference<TextView> textViewReference;
    private final Random rand = new Random();

    QuoteLoader(Activity activity, TextView textView) {
        this.activityReference = new WeakReference<>(activity);
        this.textViewReference = new WeakReference<>(textView);
    }

    /**
     *
     * @param voids  Nothing
     * @return A random quote
     */
    @Override
    protected String doInBackground(Void... voids) {
        try {
            List<String> quotes = readQuotes();
            if (quotes.size() > 0) {
                return quotes.get(rand.nextInt(quotes.size()));
            }
        } catch (final IOException e) {
            Log.e("QuoteLoader", "Error reading quotes", e);
            activityReference.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activityReference.get(),
                            "Issue loading quotes: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
        return "\"There are no quotes\" - Matt Insko";
    }

    /**
     * Displays the quote on the screen.
     * @param quote The quote to display
     */
    @Override
    protected void onPostExecute(String quote) {
        if (quote != null) {
            textViewReference.get().setText(quote);
        }
    }

    /**
     * Reads all the quotes and returns them.
     * @return The list of quotes
     * @throws IOException If it occurs
     */
    private List<String> readQuotes() throws IOException {
        InputStream in = activityReference.get().getResources().openRawResource(R.raw.quotes);
        List<String> quotes = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = reader.readLine();
            while (line != null) {
                if (!line.startsWith("#")) {
                    quotes.add(line);
                }
                line = reader.readLine();
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return quotes;
    }
}
