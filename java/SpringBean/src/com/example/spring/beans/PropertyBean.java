/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.beans;

import com.example.spring.util.Output;

/**
 * A bean to show properties
 */
public class PropertyBean extends OutputBean {

    /**
     * Construct the bean with a string
     *
     * @param str The String
     */
    public PropertyBean(String str) {
        super();
        string = str;
    }

    /**
     * Construct the bean with another bean
     *
     * @param otherBean The other bean.
     */
    public PropertyBean(PropertyBean otherBean) {
        super();
        this.otherBean = otherBean;
        string = "";
    }

    /**
     * Outputs the values of each property
     */
    @Override
    public void output() {
        super.output();
        int num = getNumber();
        Output.println("number: " + num);
        String str = getString();
        Output.println("string: " + str);
        boolean b = isOn();
        Output.println("on: " + b);
        PropertyBean pb = getOtherBean();
        Output.println("otherBean: " + ((pb != null) ? pb.getString() : "null"));
    }

    /**
     * Gets the number
     *
     * @return the number
     */
    public int getNumber() {
        outputCalledMethod();
        return number;
    }

    /**
     * Sets the number
     *
     * @param number The number
     */
    public void setNumber(int number) {
        outputCalledMethod();
        this.number = number;
    }

    /**
     * Gets the string
     *
     * @return the string
     */
    public String getString() {
        outputCalledMethod();
        return string;
    }

    /**
     * Sets the string
     *
     * @param string the string
     */
    public void setString(String string) {
        outputCalledMethod();
        this.string = string;
    }

    /**
     * Determines if on is true
     *
     * @return true (if on is true)
     */
    public boolean isOn() {
        outputCalledMethod();
        return on;
    }

    /**
     * Sets on
     *
     * @param on The new on value
     */
    public void setOn(boolean on) {
        outputCalledMethod();
        this.on = on;
    }

    /**
     * Gets the other bean
     *
     * @return The other bean
     */
    public PropertyBean getOtherBean() {
        outputCalledMethod();
        return otherBean;
    }

    /**
     * Sets the other bean
     *
     * @param otherBean The other bean
     */
    public void setOtherBean(PropertyBean otherBean) {
        outputCalledMethod();
        this.otherBean = otherBean;
    }

    private int number;
    private String string;
    private boolean on;
    private PropertyBean otherBean;
}
