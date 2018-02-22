/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.debug;

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
import java.text.NumberFormat;
import com.dasho.android.debug.other.ApplicationLogic;


/**
 * The Fibonacci calculator
 *
 * @author Matt Insko
 */
public class FibActivity extends Activity implements OnClickListener {

    static boolean check = false;
    private String num = "";
    private TextView fibNum;
    private EditText seqNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fib_main);
        findViewById(R.id.calcFibBtn).setOnClickListener(this);
        seqNum = (EditText)findViewById(R.id.fibSeqNum);
        fibNum = (TextView)findViewById(R.id.calcFibRes);
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
        if (getResources().getString(R.string.fibCalcProc).equals(fibNum.getText())) {
            Toast.makeText(getApplicationContext(), R.string.fibProc, Toast.LENGTH_SHORT).show();
            return;
        }
        fibNum.setText(R.string.fibCalcProc);
        try {
            num = seqNum.getText().toString();
            int seq = Integer.parseInt(num);
            if (seq > 50) {
                num = "50";
                Toast.makeText(getApplicationContext(), R.string.fibTooLarge, Toast.LENGTH_LONG).show();
            } else if (seq < 1) {
                showNumberError();
                return;
            } else if (seq > 30) {
                Toast.makeText(getApplicationContext(), R.string.fibLarge, Toast.LENGTH_SHORT).show();
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
        FibTask task = new FibTask();
        task.execute(Integer.valueOf(num));
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
    }

    /**
     * Restores the last request
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (!ApplicationLogic.usingDashO()) {
            toast("PreEmptive Protection - DashO was not used.");
        } else {
            if (check) {
                toast("This app is being debugged.");
            } else {
                toast("This app is not being debugged.");
            }
        }
        SharedPreferences prefs = getSharedPreferences("FibPrefs", MODE_PRIVATE);
        num = prefs.getString("seq", getString(R.string.fibSeqDef));
        seqNum.setText(num);
        seqNum.setSelection(num.length());
    }

    /**
     * Makes a short toast
     *
     * @param txt The toast.
     */
    private void toast(String txt) {
        Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
    }

    /**
     * An async task to calculate the number.
     *
     * @author Matt Insko
     */
    private class FibTask extends AsyncTask<Integer, Void, Integer> {

        private int seqNum;
        private NumberFormat nf = NumberFormat.getIntegerInstance();

        @Override
        protected Integer doInBackground(Integer... params) {
            seqNum = params[0].intValue();
            return Integer.valueOf(getFib(seqNum));
        }

        /**
         * Calculates the Fibonacci number at a certain location
         *
         * @param num The location
         * @return The Fibonacci number at that location
         */
        private int getFib(int num) {
            if (num < 3) {
                return 1;
            } else {
                return getFib(num - 1) + getFib(num - 2);
            }
        }

        @Override
        protected void onPostExecute(Integer result) {
            fibNum.setText(nf.format(result));
        }

    }
}
