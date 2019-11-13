/* Copyright 2019 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.kotlinSample

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import kotlinx.android.synthetic.main.random_main.genBtn
import kotlinx.android.synthetic.main.random_main.genNumber
import kotlinx.android.synthetic.main.random_main.rndMaxNum
import kotlinx.android.synthetic.main.random_main.rndMinNum
import java.text.NumberFormat
import java.util.Random


/**
 * The Random Number Generator
 * @author Matt Insko
 */
class RandomGenActivity : Activity(), OnClickListener {

    private var minStr: String? = null
    private var maxStr: String? = null
    private val nf = NumberFormat.getIntegerInstance()
    private val rnd = Random(System.currentTimeMillis())

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.random_main)
        genBtn.setOnClickListener(this)
    }

    /**
     * Processes the button click
     */
    override fun onClick(v: View) {
        if (v == genBtn) {
            genNumber.text = ""
            try {
                minStr = rndMinNum.text.toString()
                val minInt = minStr?.toInt() ?: 0
                maxStr = rndMaxNum.text.toString()
                val maxInt = maxStr?.toInt() ?: 100
                if (minInt < 0 || maxInt < 0) {
                    Toast.makeText(applicationContext, R.string.badNum, Toast.LENGTH_SHORT)
                            .show()
                }
                if (maxInt <= minInt) {
                    Toast.makeText(applicationContext, R.string.minMaxErr, Toast.LENGTH_SHORT)
                            .show()
                    return
                }
                genNumber.text = nf.format(findRnd(minInt, maxInt))
            } catch (e: NumberFormatException) {
                val dlgAlert = AlertDialog.Builder(this)
                dlgAlert.setMessage(R.string.badNum)
                dlgAlert.setTitle(R.string.errTitle)
                dlgAlert.setPositiveButton(Resources.getSystem().getText(android.R.string.ok), null)
                dlgAlert.setCancelable(true)
                dlgAlert.create().show()
            }
        }
    }

    /**
     * Saves the request
     */
    override fun onPause() {
        super.onPause()
        val prefs = getSharedPreferences("RndPrefs", Context.MODE_PRIVATE)
        val prefEditor = prefs.edit()
        prefEditor.putString("min", minStr)
        prefEditor.putString("max", maxStr)
        prefEditor.apply()
    }

    /**
     * Restores the last request
     */
    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences("RndPrefs", Context.MODE_PRIVATE)
        minStr = prefs.getString("min", "1")
        maxStr = prefs.getString("max", "100")
        rndMinNum.setText(minStr)
        rndMinNum.setSelection(minStr?.length ?: 0)
        rndMaxNum.setText(maxStr)
        rndMaxNum.setSelection(maxStr?.length ?: 0)
    }

    /**
     * Finds the random number
     * @param min The minimum
     * @param max The maximum
     * @return The random number
     */
    private fun findRnd(min: Int, max: Int): Long {
        val range = max - min + 1
        return ((rnd.nextDouble() * range).toInt() + min).toLong()
    }
}
