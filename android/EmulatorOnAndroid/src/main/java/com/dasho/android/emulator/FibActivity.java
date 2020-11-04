/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.emulator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * The Fibonacci calculator
 *
 * @author Matt Insko
 */
public class FibActivity extends Activity implements OnClickListener {

    private String num = "";
    private TextView fibNum;
    private EditText seqNum;
    private FindFibTask fibTask;
    private static final int WARN_SEQUENCE = 30;
    private static final int MAX_SEQUENCE = 50;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fib_main);
        findViewById(R.id.calcFibBtn).setOnClickListener(this);
        seqNum = findViewById(R.id.fibSeqNum);
        fibNum = findViewById(R.id.calcFibRes);
        fibTask = new FindFibTask(this, fibNum, getString(R.string.fibCalcProgress));
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
            fibTask.find(seq);
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
}
