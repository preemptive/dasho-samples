/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.emulator;

import android.app.Activity;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;

/**
 * Finds the Fibonacci number at a given position.
 */
public class FindFibTask {
    private boolean cancelled;
    private int max;
    private Thread runningThread;
    private final WeakReference<Activity> callingActivity;
    private final WeakReference<TextView> outputText;
    private final String progressString;
    private final NumberFormat nf = NumberFormat.getIntegerInstance();

    public FindFibTask(Activity activity, TextView fibNum, String progressString) {
        this.outputText = new WeakReference<>(fibNum);
        this.callingActivity = new WeakReference<>(activity);
        this.progressString = progressString;
    }

    public void cancel() {
        cancelled = true;
    }

    /**
     * Finds and displays the Fibonacci number at the given position.
     * @param position The position in the sequence of Fibonacci numbers.
     */
    public void find(final int position) {
        reset();
        runningThread = new Thread(()-> {
            final long number = getFib(position);
            runOnUI(() -> outputResult(number));
        });
        runningThread.start();
    }

    private void reset() {
        cancelled = false;
        max = 0;
        if (runningThread != null) {
            runningThread.interrupt();
        }
    }

    /**
     * Calculates the Fibonacci number at a certain position.
     *
     * @param num The position.
     * @return The Fibonacci number at that position.
     */
    private long getFib(int num) {
        long result;
        if (num < 3 || cancelled) {
            result =  1;
        } else {
            result = getFib(num - 1) + getFib(num - 2);
        }
        reportMax(num);
        return result;
    }

    /**
     * Reports the current progress.
     * @param num The position currently calculated.
     */
    private void reportMax(int num) {
        if (num > max) {
            runOnUI(() -> {
                TextView field = outputText.get();
                if (field != null) {
                    field.setText(String.format(progressString, num));
                }
            });
            max = num;
        }
    }

    /**
     * Displays the final result.
     * @param result The result.
     */
    private void outputResult(long result) {
        TextView field = outputText.get();
        if (field != null) {
            if (cancelled) {
                field.setText("");
            } else {
                field.setText(nf.format(result));
            }
        }
    }

    /**
     * Runs something on the UI thread.
     * @param runnable The code to execute on the UI thread.
     */
    private void runOnUI(Runnable runnable) {
        Activity activity = callingActivity.get();
        if (activity != null) {
            activity.runOnUiThread(runnable);
        }
    }

}
