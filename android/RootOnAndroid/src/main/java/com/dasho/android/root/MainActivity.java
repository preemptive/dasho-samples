/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.root;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.dasho.android.root.other.ApplicationLogic;

/**
 * The main screen for this app
 *
 * @author Matt Insko
 */
public class MainActivity extends Activity implements OnClickListener {
    private static boolean initializedLogic = false;

    /**
     * Used by root response
     *
     * @return True if rooted.
     */
    public static boolean isInitialized() {
        return initializedLogic;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.GenActBtn).setOnClickListener(this);
        findViewById(R.id.FibActBtn).setOnClickListener(this);
        initializedLogic = new ApplicationLogic(getApplicationContext()).someApplicationLogic();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            showDialog(R.string.abtTitle, R.string.abtMsg);
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    /**
     * Handles the button clicks
     *
     * @param v The view clicked
     */
    public void onClick(View v) {
        if (!ApplicationLogic.usingDashO()) {
            toast("PreEmptive Protection - DashO was not used.");
        } else if (!ApplicationLogic.usingCheck()) {
            toast("Root Check was not used in this build.");
        } else if (initializedLogic) {
            toast("This app is running on a rooted device or emulator.");
        }
        switch (v.getId()) {
            case R.id.GenActBtn:
                startActivity(new Intent(getApplicationContext(), RandomGenActivity.class));
                break;
            case R.id.FibActBtn:
                startActivity(new Intent(getApplicationContext(), FibActivity.class));
                break;
        }
    }

    /**
     * Shows a dialog
     *
     * @param title   The title id
     * @param message The message id
     */
    private void showDialog(int title, int message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setTitle(title);
        dlgAlert.setMessage(message);
        dlgAlert.setPositiveButton(Resources.getSystem().getText(android.R.string.ok), null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    /**
     * Makes a short toast
     *
     * @param txt The toast.
     */
    private void toast(String txt) {
        Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
    }
}
