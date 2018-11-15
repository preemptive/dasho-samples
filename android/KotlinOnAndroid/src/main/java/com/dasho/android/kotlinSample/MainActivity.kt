/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.kotlinSample

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import kotlinx.android.synthetic.main.activity_main.*


/**
 * The main screen for this app
 * @author Matt Insko
 */
class MainActivity : Activity(), OnClickListener {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GenActBtn.setOnClickListener(this)
        FibActBtn.setOnClickListener(this)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            showAboutDialog()
            return true
        }
        return super.onMenuItemSelected(featureId, item)
    }

    /**
     * Handles the button clicks
     * @param v The view clicked
     */
    override fun onClick(v: View) {
        when (v) {
            GenActBtn -> startActivity(Intent(applicationContext, RandomGenActivity::class.java))
            FibActBtn -> startActivity(Intent(applicationContext, FibActivity::class.java))
        }
    }

    /**
     * Shows the about dialog
     */
    private fun showAboutDialog() {
        val dlgAlert = AlertDialog.Builder(this)
        dlgAlert.setTitle(R.string.abtTitle)
        dlgAlert.setMessage(R.string.abtMsg)
        dlgAlert.setPositiveButton(Resources.getSystem().getText(android.R.string.ok), null)
        dlgAlert.setCancelable(true)
        dlgAlert.create().show()
    }
}
