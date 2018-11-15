/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.hook.other;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * This class could contain some business logic.
 * It is used for the Hook Check.
 */
public class ApplicationLogic {

    private boolean myBoolean = false;
    private static boolean usingCheck=false;//Used to verify DashO has been run correctly.
    private Context context;

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
     * @param b the result of the check
     */
    @SuppressWarnings("unused") //Used by the check
    private void setupVars(boolean b) {
        usingCheck=true;
        myBoolean=b;
    }

    /**
     * Hook detection requires this method.
     *
     * @return The original application's context.
     */
    @SuppressWarnings("unused") //Used by the check
    public Context getApplicationContext() {
        return context;
    }

    public static boolean usingCheck() {
        return usingCheck;
    }

    /**
     * A check to see if PreEmptive Protection - DashO had been run.
     */
    @TargetApi(24)
    public static boolean usingDashO() {
        if (usingCheck) {
            return true;
        }
        try {
            Method methods[] = Class.forName("com.dasho.android.hook.other.ApplicationLogic").getDeclaredMethods();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Arrays.stream(methods).anyMatch(m-> m.getName().length()==1);
            } else {
                for (Method m : methods) {
                    if (m.getName().length() == 1) {
                        return true;
                    }
                }
            }
        } catch (ClassNotFoundException ignored) {}
        return false;
    }

}
