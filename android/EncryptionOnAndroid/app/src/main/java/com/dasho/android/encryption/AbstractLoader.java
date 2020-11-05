/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.encryption;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Random;

public class AbstractLoader<T extends View> {
    protected final WeakReference<Activity> activityReference;
    protected final WeakReference<T> viewReference;
    protected final Random rand = new Random();

    AbstractLoader (Activity activity, T view) {
        this.activityReference = new WeakReference<>(activity);
        this.viewReference = new WeakReference<>(view);
    }

    /**
     * Runs something on the UI thread.
     * @param runnable The code to execute on the UI thread.
     */
    protected void runOnUIThread(Runnable runnable) {
        Activity activity = activityReference.get();
        if (activity != null) {
            activity.runOnUiThread(runnable);
        }
    }

    /**
     * Shows a toast on the UI thread.
     * @param message The message to toast.
     * @param winded Is it a long toast?
     */
    protected void toastOnUIThread(String message, boolean winded) {
        Activity activity = activityReference.get();
        if (activity != null) {
            activity.runOnUiThread(()-> Toast.makeText(activity, message, winded ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show());
        }
    }
}
