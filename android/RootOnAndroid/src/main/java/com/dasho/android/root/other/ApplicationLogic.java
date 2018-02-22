/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.dasho.android.root.other;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class could contain some business logic. 
 * It is used for the Root Check.
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
     * @return A variable which will ultimately be set by the root detection.
     */
    public boolean someApplicationLogic() {
        return myBoolean;
    }



    private void setupVars(boolean b) {
        usingCheck=true;
        myBoolean=b;
    }

    /**
     * RootDetection requires this method.
     *
     * @return The original application's context.
     */
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
            Method methods[] = Class.forName("com.dasho.android.root.other.ApplicationLogic").getDeclaredMethods();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Arrays.stream(methods).filter(m-> m.getName().length()==1).findAny().isPresent();
            } else {
                for (Method m : methods) {
                    if (m.getName().length() == 1) {
                        return true;
                    }
                }
            }
        } catch (ClassNotFoundException cnfe) {}
        
        return false;
    }

}
