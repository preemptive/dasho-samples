/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.emulator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.dasho.android.emulator.other.ApplicationLogic;

/**
 * The main screen for this app
 *
 * @author Matt Insko
 */
public class MainActivity extends Activity {
    private static boolean initializedLogic = false;

    /**
     * Used by emulator response
     *
     * @return True if on an emulator.
     */
    @SuppressWarnings({"unused", "RedundantSuppression"}) //Used by the response
    public static boolean isInitialized() {
        return initializedLogic;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.GenActBtn).setOnClickListener(
                ignored -> toastAndLaunch(RandomGenActivity.class));
        findViewById(R.id.FibActBtn).setOnClickListener(
                ignored -> toastAndLaunch(FibActivity.class));
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
            showAboutDialog();
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    /**
     * Displays an optional toast and launches the clazz.
     * @param clazz The activity to launch
     */
    public void toastAndLaunch(Class<?> clazz) {
        if (!ApplicationLogic.wasDashOUsed()) {
            toast("DashO was not used.");
        } else if (!ApplicationLogic.wasRenamingApplied()) {
            toast("DashO was used, but R8 was not used.");
        } else if (!ApplicationLogic.usingCheck()) {
            toast("Emulator Check was not used in this build.");
        } else if (initializedLogic) {
            toast("This app is running on an emulator.");
        }
        startActivity(new Intent(getApplicationContext(), clazz));
    }

    /**
     * Shows the about dialog
     *
     */
    private void showAboutDialog() {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setTitle(R.string.abtTitle);
        dlgAlert.setMessage(R.string.abtMsg);
        dlgAlert.setPositiveButton(Resources.getSystem().getText(android.R.string.ok), null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    /**
     * Shows a long toast
     *
     * @param txt The toast.
     */
    private void toast(String txt) {
        Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
    }
}
