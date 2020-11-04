/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.hook.other;

import android.content.Context;

/**
 * This class could contain some business logic.
 * It is used for the Hook Check.
 */
public class ApplicationLogic {

    private boolean myBoolean = false;
    private final Context context;

    // This flag is only used to verify that DashO has been run correctly.
    private static boolean dashOWasUsed = false;

    public ApplicationLogic(Context context) {
        this.context = context;
    }

    /**
     * This could be some logic run during application initialization or at some
     * other time.
     *
     * @return A variable which will ultimately be set by the hook detection.
     */
    public boolean someApplicationLogic() {
        return myBoolean;
    }

    /**
     * Used by the check
     * @param triggered the result of the check
     */
    @SuppressWarnings({"unused", "RedundantSuppression"}) //Used by the check
    private void setupVars(boolean triggered) {
        dashOWasUsed = true;
        myBoolean = triggered;
    }

    /**
     * Hook detection requires this method.
     *
     * @return The original application's context.
     */
    @SuppressWarnings({"unused", "RedundantSuppression"}) //Used by the check
    public Context getApplicationContext() {
        return context;
    }

    public static boolean wasDashOUsed() {
        return dashOWasUsed;
    }

    public static boolean wasRenamingApplied() {
        try {
            // Prevent R8 from recognizing and unintentionally "fixing" this string by replacing it with the class's new
            // name.
            Class.forName("xcom.dasho.android.hook.other.ApplicationLogic".substring(1));
            return false;
        } catch (ClassNotFoundException ignored) {
            return true;
        }
    }
}
