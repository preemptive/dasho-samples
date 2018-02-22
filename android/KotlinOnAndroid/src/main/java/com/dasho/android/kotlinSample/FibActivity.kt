/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.kotlinSample

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import java.text.NumberFormat

import kotlinx.android.synthetic.main.fib_main.*

/**
 * The Fibonacci calculator
 * @author Matt Insko
 */
class FibActivity : Activity(), OnClickListener {

    private var num = ""

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fib_main)
        calcFibBtn.setOnClickListener(this)
    }

    /**
     * Processes the button click
     */
    override fun onClick(v: View) {
        if (v == calcFibBtn) {
            processFibRequest()
        }
    }

    /**
     * Processes the request.
     */
    private fun processFibRequest() {
        if (resources.getString(R.string.fibCalcProc) == calcFibRes.text) {
            Toast.makeText(applicationContext, R.string.fibProc, Toast.LENGTH_SHORT).show()
            return
        }
        calcFibRes.setText(R.string.fibCalcProc)
        try {
            num = fibSeqNum.text.toString()
            val seq = num.toInt()
            if (seq > 40) {
                num = "40"
                Toast.makeText(applicationContext, R.string.fibTooLarge, Toast.LENGTH_SHORT)
                        .show()
                calcFibRes.text = ""
            } else if (seq < 1) {
                showNumberError()
                return
            } else if (seq > 30) {
                Toast.makeText(applicationContext, R.string.fibLarge, Toast.LENGTH_SHORT)
                        .show()
            }
            findFib()
        } catch (e: NumberFormatException) {
            showNumberError()
        }

    }

    /**
     * Shows an error
     */
    private fun showNumberError() {
        val dlgAlert = AlertDialog.Builder(this)
        dlgAlert.setMessage(R.string.badNum)
        dlgAlert.setTitle(R.string.errTitle)
        dlgAlert.setPositiveButton(Resources.getSystem().getText(android.R.string.ok), null)
        dlgAlert.setCancelable(true)
        dlgAlert.create().show()
        calcFibRes.text = ""
    }

    /**
     * Finds the Fibonacci number at that sequence index
     */
    private fun findFib() {
        val task = FibTask()
        task.execute(Integer.valueOf(num))
    }

    /**
     * Saves the request
     */
    override fun onPause() {
        super.onPause()
        val prefs = getSharedPreferences("FibPrefs", Context.MODE_PRIVATE)
        val prefEditor = prefs.edit()
        prefEditor.putString("seq", num)
        prefEditor.apply()
    }

    /**
     * Restores the last request
     */
    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences("FibPrefs", Context.MODE_PRIVATE)
        num = prefs.getString("seq", getString(R.string.fibSeqDef))
        fibSeqNum.setText(num)
        fibSeqNum.setSelection(num.length)
    }

    /**
     * An async task to calculate the number.
     * @author Matt Insko
     */
    private inner class FibTask : AsyncTask<Int, Void, Int>() {

        private var seqNum: Int = 0
        private val nf = NumberFormat.getIntegerInstance()

        /**
         * Add a FeatureStart("Fibonacci") virtual annotation
         */
        override fun doInBackground(vararg params: Int?): Int {
            seqNum = params[0] ?: 1
            return Integer.valueOf(getFib(seqNum))
        }

        /**
         * Calculates the Fibonacci number at a certain location
         * @param num The location
         * @return The Fibonacci number at that location
         */
        private fun getFib(num: Int): Int = if (num < 3) { 1 }
            else { getFib(num - 1) + getFib(num - 2) }

        /**
         * Shows the result on the screen
         */
        override fun onPostExecute(result: Int?) {
            calcFibRes.text = nf.format(result)
        }

    }
}
