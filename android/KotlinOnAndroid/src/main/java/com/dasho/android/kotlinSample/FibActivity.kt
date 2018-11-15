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
import android.widget.TextView
import java.lang.ref.WeakReference



const val WARN_SEQUENCE = 30
const val MAX_SEQUENCE = 50

/**
 * The Fibonacci calculator
 * @author Matt Insko
 */
class FibActivity : Activity(), OnClickListener {

    private var num = ""
    private var fibTask: FibTask? = null
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
        if (calcFibRes.text.startsWith(getString(R.string.fibCalcProcessing))) {
            Toast.makeText(applicationContext, R.string.fibProc, Toast.LENGTH_SHORT).show()
            return
        }
        calcFibRes.setText(R.string.fibCalcProcessing)
        try {
            num = fibSeqNum.text.toString()
            val seq = num.toInt()
            when {
                seq > MAX_SEQUENCE -> {
                    num = Integer.toString(MAX_SEQUENCE)
                    Toast.makeText(applicationContext, getString(R.string.fibTooLarge, MAX_SEQUENCE) , Toast.LENGTH_SHORT)
                            .show()
                    fibSeqNum.setText(num)
                }
                seq < 1 -> {
                    showNumberError()
                    return
                }
                seq > WARN_SEQUENCE -> Toast.makeText(applicationContext, R.string.fibLarge, Toast.LENGTH_SHORT)
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
        fibTask = FibTask(calcFibRes, getString(R.string.fibCalcProgress))
        fibTask?.execute(Integer.valueOf(num))
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
        fibTask?.cancel()
    }

    /**
     * Restores the last request
     */
    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences("FibPrefs", Context.MODE_PRIVATE)
        num = prefs.getString("seq", getString(R.string.fibSeqDef))?:"11"
        fibSeqNum.setText(num)
        fibSeqNum.setSelection(num.length)
    }

    /**
     * An async task to calculate the number.
     *
     * @author Matt Insko
     */
    private class FibTask(fibNum: TextView, private val progressString: String) : AsyncTask<Int, Int, Long>() {

        private var cancelled: Boolean = false
        private var seqNum: Int = 0
        private var max: Int = 0
        private val outputText: WeakReference<TextView> = WeakReference(fibNum)
        private val nf = NumberFormat.getIntegerInstance()

        fun cancel() {
            cancelled = true
        }

        override fun doInBackground(vararg params: Int?): Long? {
            seqNum = params[0] ?: 1
            return getFib(seqNum)
        }

        /**
         * Calculates the Fibonacci number at a certain location
         *
         * @param num The location
         * @return The Fibonacci number at that location
         */
        private fun getFib(num: Int): Long {
            val result = if (num < 3 || cancelled) {
                1
            } else {
                getFib(num - 1) + getFib(num - 2)
            }
            reportMax(num)
            return result
        }

        private fun reportMax(num: Int) {
            if (num > max) {
                publishProgress(num)
                max = num
            }
        }

        override fun onPostExecute(result: Long?) {
            val field = outputText.get()
            if (field != null) {
                if (cancelled) {
                    field.text = ""
                } else {
                    field.text = nf.format(result)
                }
            }
        }

        override fun onProgressUpdate(vararg values: Int?) {
            val field = outputText.get()
            if (field != null) {
                field.text = String.format(progressString, values[0])
            }
        }
    }
}
