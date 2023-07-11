package com.example.sample.app;

import com.example.sample.core.expcore.CoreUtils;

public class App {

    public static void main(String[] ags) {
        System.out.println("App started (App.class=" + App.class + ")" );

        String s = "Started main in core > ";
        System.out.println("1. s=" + s);

        s = CoreUtils.appendToString(s);
        System.out.println("2. s=" + s);
    }

}
