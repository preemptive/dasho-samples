/* Copyright 2020 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.root.other;

import android.content.Context;

/**
 * This class could contain some business logic.
 * It is used for the Root Check.
 */
public class ApplicationLogic {

    private boolean myBoolean = false;
    private final Context context;

    // These flags are only used to verify that DashO has been run correctly.
    private static boolean usingCheck = false;
    private static boolean injectionApplied = false;

    public ApplicationLogic(Context context) {
        this.context = context;
    }

    /**
     * This could be some logic run during application initialization or at some
     * other time.
     *
     * @return A variable which will ultimately be set by the root detection.
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
        usingCheck = true;
        myBoolean = triggered;
    }

    /**
     * Used by a check just to report that DashO injection was applied correctly.
     * @param ignored an unused check result
     */
    @SuppressWarnings({"unused", "RedundantSuppression"}) // only referenced in the DashO config
    private void setupInjectionWasApplied(boolean ignored) {
        injectionApplied = true;
    }

    /**
     * Root detection requires this method.
     *
     * @return The original application's context.
     */
    @SuppressWarnings({"unused", "RedundantSuppression"}) //Used by the check
    public Context getApplicationContext() {
        return context;
    }

    public static boolean usingCheck() {
        return usingCheck;
    }

    @SuppressWarnings({"BooleanMethodIsAlwaysInverted", "RedundantSuppression"})
    public static boolean wasDashOUsed() {
        return usingCheck || injectionApplied;
    }

    @SuppressWarnings({"BooleanMethodIsAlwaysInverted", "RedundantSuppression"})
    public static boolean wasRenamingApplied() {
        try {
            // Prevent R8 from recognizing and unintentionally "fixing" this string by replacing it with the class's new
            // name.
            Class.forName("xcom.dasho.android.root.other.ApplicationLogic".substring(1));
            return false;
        } catch (ClassNotFoundException ignored) {
            return true;
        }
    }
}
