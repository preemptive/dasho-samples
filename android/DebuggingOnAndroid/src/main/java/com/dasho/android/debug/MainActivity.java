/* Copyright 2019 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.debug;

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
import com.dasho.android.debug.other.ApplicationLogic;


/**
 * The main screen for this app
 *
 * @author Matt Insko
 */
public class MainActivity extends Activity implements OnClickListener {
    private static boolean initializedLogic = false;

    /**
     * Used by debug enabled response
     *
     * @return True if debugging is enabled.
     */
    @SuppressWarnings("unused") //Used by the response
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
        //You could check the value of initializedLogic and perform a custom action here (like sending a message to Google Analytics)
//        if (initializedLogic) {
//            mTracker.send(new HitBuilders.EventBuilder()
//                .setCategory("DebugEnabled")
//                .setAction("Report")
//                .build());
//        }
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
     * Handles the button clicks
     *
     * @param v The view clicked
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.GenActBtn:
                if (!ApplicationLogic.wasDashOUsed()) {
                    toast("DashO was not used.");
                } else if (!ApplicationLogic.wasRenamingApplied()) {
                    toast("DashO was used, but R8 was not used.");
                } else if (initializedLogic) {
                    toast("This app has debugging enabled.");
                } else {
                    toast("This app does not have debugging enabled.");
                }

                startActivity(new Intent(getApplicationContext(), RandomGenActivity.class));
                break;
            case R.id.FibActBtn:
                startActivity(new Intent(getApplicationContext(), FibActivity.class));
                break;
        }
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
     * Makes a short toast
     *
     * @param txt The toast.
     */
    private void toast(String txt) {
        Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
    }
}
