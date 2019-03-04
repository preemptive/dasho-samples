/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.beans;

import com.example.spring.util.Output;

/**
 * A Bean which can output data
 */
public class OutputBean {

    /**
     * Constructs the bean.
     */
    public OutputBean() {
        this.name = getClass().getName();
    }

    /**
     * Output data about the bean
     */
    public void output() {
        Output.println("Name: " + name);
    }

    /**
     * Output the calling method.
     */
    protected void outputCalledMethod() {
        Output.println(name + "." + Output.getCallingMethodName() + "() called.");
    }

    protected final String name;
}
