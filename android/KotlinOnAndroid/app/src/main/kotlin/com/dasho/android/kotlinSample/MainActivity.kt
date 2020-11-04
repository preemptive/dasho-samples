/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
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
import android.widget.Toast
import com.dasho.android.kotlinSample.other.ApplicationLogic
import kotlinx.android.synthetic.main.activity_main.FibActBtn
import kotlinx.android.synthetic.main.activity_main.GenActBtn


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
        ApplicationLogic(applicationContext).someApplicationLogic()
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
        if (!ApplicationLogic.wasDashOUsed()) {
            toast("DashO was not used.")
        } else if (!ApplicationLogic.wasRenamingApplied()) {
            toast("DashO was used, but R8 was not used.")
        }

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

    /**
     * Shows a long toast
     *
     * @param message The toast message
     */
    private fun toast(message:String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}
