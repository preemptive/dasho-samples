package com.example.sample.core.expcore;

import com.example.sample.core.pricore.InternalCore;

/**
 * This is a public exported package
 */
public class CoreUtils {

    public static String appendToString(String s) {
        return s + " ( CoreUtils.class=" + CoreUtils.class + ")  exportedCore works, " + InternalCore.appendToString() + " ";
    }

}
