/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
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
import kotlinx.android.synthetic.main.fib_main.calcFibBtn
import kotlinx.android.synthetic.main.fib_main.calcFibRes
import kotlinx.android.synthetic.main.fib_main.fibSeqNum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.NumberFormat


const val WARN_SEQUENCE = 30
const val MAX_SEQUENCE = 50

/**
 * The Fibonacci calculator
 * @author Matt Insko
 */
class FibActivity : Activity(), OnClickListener {

    private var num = ""
    private var max = 0
    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)
    private val nf = NumberFormat.getIntegerInstance()
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
                    num = MAX_SEQUENCE.toString()
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
        max = 0
        coroutineScope.launch(Dispatchers.Main) {
            calcFibRes.text = nf.format(getFibAsync(num.toInt()).await())
        }
    }


    /**
     * Finds the Fibonacci number via a coroutine
     * @param num The sequence number
     */
    private fun getFibAsync(num: Int): Deferred<Long> =
        coroutineScope.async(Dispatchers.Default) {
            return@async calcFib(num)
        }

    /**
     * Reports the progress
     * @param currentNum The sequence number just calculated
     */
    private fun reportMax(currentNum: Int) {
        if (max < currentNum) {
            max = currentNum
            coroutineScope.launch(Dispatchers.Main) {
                calcFibRes.text = resources.getString(R.string.fibCalcProgress, max)
            }
        }
    }

    /**
     * Calculates the Fibonacci number at a certain location
     *
     * @param num The location
     * @return The Fibonacci number at that location
     */
    private fun calcFib(num: Int): Long {
        val result = if (num < 3) {
            1
        } else {
            calcFib(num - 1) + calcFib(num - 2)
        }
        reportMax(num)
        return result
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
        num = prefs.getString("seq", getString(R.string.fibSeqDef))?:"11"
        fibSeqNum.setText(num)
        fibSeqNum.setSelection(num.length)
    }
}
