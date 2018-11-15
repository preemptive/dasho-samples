/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.root;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;


/**
 * The Fibonacci calculator
 *
 * @author Matt Insko
 */
public class FibActivity extends Activity implements OnClickListener {

    private String num = "";
    private TextView fibNum;
    private EditText seqNum;
    private FibTask fibTask;
    private static final int WARN_SEQUENCE = 30;
    private static final int MAX_SEQUENCE = 50;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fib_main);
        findViewById(R.id.calcFibBtn).setOnClickListener(this);
        seqNum = findViewById(R.id.fibSeqNum);
        fibNum = findViewById(R.id.calcFibRes);
    }

    /**
     * Processes the button click
     */
    public void onClick(View v) {
        if (v.getId() == R.id.calcFibBtn) {
            processFibRequest();
        }
    }

    /**
     * Processes the request
     */
    private void processFibRequest() {
        if (fibNum.getText().toString().startsWith(getResources().getString(R.string.fibCalcProcessing))) {
            Toast.makeText(getApplicationContext(), R.string.fibProc, Toast.LENGTH_SHORT).show();
            return;
        }
        fibNum.setText(R.string.fibCalcProcessing);
        try {
            num = seqNum.getText().toString();
            int seq = Integer.parseInt(num);
            if (seq > MAX_SEQUENCE) {
                num = Integer.toString(MAX_SEQUENCE);
                Toast.makeText(getApplicationContext(), getString(R.string.fibTooLarge, MAX_SEQUENCE), Toast.LENGTH_LONG)
                        .show();
                seqNum.setText(num);
            } else if (seq < 1) {
                showNumberError();
                return;
            } else if (seq > WARN_SEQUENCE) {
                Toast.makeText(getApplicationContext(), R.string.fibLarge, Toast.LENGTH_SHORT)
                        .show();
            }
            findFib();
        } catch (NumberFormatException e) {
            showNumberError();
        }
    }

    /**
     * Shows an error
     */
    private void showNumberError() {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(R.string.badNum);
        dlgAlert.setTitle(R.string.errTitle);
        dlgAlert.setPositiveButton(Resources.getSystem().getText(android.R.string.ok), null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
        fibNum.setText("");
    }

    /**
     * Finds the Fibonacci number at that sequence index
     */
    private void findFib() {
        fibTask = new FibTask(fibNum, getString(R.string.fibCalcProgress));
        fibTask.execute(Integer.valueOf(num));
    }

    /**
     * Saves the request
     */
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences("FibPrefs", MODE_PRIVATE);
        Editor prefEditor = prefs.edit();
        prefEditor.putString("seq", num);
        prefEditor.apply();
        if (fibTask != null) {
            fibTask.cancel();
        }
    }

    /**
     * Restores the last request
     */
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("FibPrefs", MODE_PRIVATE);
        num = prefs.getString("seq", getString(R.string.fibSeqDef));
        seqNum.setText(num);
        seqNum.setSelection(num.length());
    }

    /**
     * An async task to calculate the number.
     *
     * @author Matt Insko
     */
    private static class FibTask extends AsyncTask<Integer, Integer, Long> {

        private boolean cancelled;
        private int seqNum;
        private int max;
        private final WeakReference<TextView> outputText;
        private final String progressString;
        private final NumberFormat nf = NumberFormat.getIntegerInstance();

        public FibTask(TextView fibNum, String progressString) {
            this.outputText = new WeakReference<>(fibNum);
            this.progressString = progressString;
        }

        private void cancel() {
            cancelled = true;
        }

        @Override
        protected Long doInBackground(Integer... params) {
            seqNum = params[0];
            return getFib(seqNum);
        }

        /**
         * Calculates the Fibonacci number at a certain location
         *
         * @param num The location
         * @return The Fibonacci number at that location
         */
        private long getFib(int num) {
            long result;
            if (num < 3 || cancelled) {
                result =  1;
            } else {
                result = getFib(num - 1) + getFib(num - 2);
            }
            reportMax(num);
            return result;
        }

        private void reportMax(int num) {
            if (num > max) {
                publishProgress(num);
                max = num;
            }
        }

        @Override
        protected void onPostExecute(Long result) {
            TextView field = outputText.get();
            if (field != null) {
                if (cancelled) {
                    field.setText("");
                } else {
                    field.setText(nf.format(result));
                }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            TextView field = outputText.get();
            if (field != null) {
                field.setText(String.format(progressString, values[0]));
            }
        }
    }
}
