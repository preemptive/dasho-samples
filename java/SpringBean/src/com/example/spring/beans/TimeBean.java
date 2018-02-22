/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.beans;

import com.example.spring.util.Output;

/**
 * A bean which holds the time of construction and a number representing order
 */
public class TimeBean extends OutputBean {

    /**
     * The contructor
     */
    public TimeBean() {
        this.time = System.currentTimeMillis();
        this.counter = ++COUNT;
    }

    /**
     * Gets the time the bean was created
     *
     * @return The time
     */
    public long getTime() {
        outputCalledMethod();
        return time;
    }

    /**
     * Gets the order in which the bean was created
     *
     * @return The order
     */
    public int getCounter() {
        outputCalledMethod();
        return counter;
    }


    @Override
    public void output() {
        super.output();
        outputCalledMethod();
        Output.println("ID = " + getCounter());
        Output.println("Time = " + getTime());
    }


    private long time;
    private int counter = 0;
    private static int COUNT = 0;
}