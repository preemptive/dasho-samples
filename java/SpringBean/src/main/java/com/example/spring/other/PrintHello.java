/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.other;

import com.example.spring.util.Output;

/**
 * Outputs Hello
 */
public class PrintHello implements IGreeting {

    /**
     * Outputs Hello and the message
     *
     * @param str The message;
     */
    public void greeting(String str) {
        Output.printCalledMethod(getClass().getName());
        Output.println("Hello: " + str);
    }
}
