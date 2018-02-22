/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.beans;

/**
 * A bean using a lookup method and has a method replaced
 */
public class MyLookupReplacedBean extends OutputBean {

    /**
     * Constructor
     *
     * @param count The number of Time Beans to request
     */
    public MyLookupReplacedBean(int count) {
        this.count = count;
    }

    /**
     * Creates a time bean. This method will be replaced by Spring (in on example)
     *
     * @return a new time bean
     */
    protected TimeBean newTimeBean() {
        outputCalledMethod();
        return new TimeBean();
    }

    /**
     * Prints out the TimeBean information
     */
    @Override
    public void output() {
        TimeBean tbs[] = new TimeBean[count];
        for (int i = 0; i < count; i++) {
            tbs[i] = newTimeBean();
        }
        for (TimeBean tb : tbs) {
            printTimeBean(tb);
        }
    }

    /**
     * Prints out the information. This method will be replaced by Spring
     *
     * @param tb The bean to print
     */
    protected void printTimeBean(TimeBean tb) {
        outputCalledMethod();
        tb.output();
    }

    private int count;
}