/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.beans;

import com.example.spring.other.IGreeting;
import com.example.spring.util.Output;

/**
 * A bean with a classname as a variable
 */
public class ClassBean extends OutputBean {

    /**
     * Constructs the bean
     *
     * @param name The class name
     */
    public ClassBean(String name) {
        super();
        this.classname = name;
    }

    /**
     * Constructs the bean with a null class name
     */
    public ClassBean() {
        this(null);
    }

    /**
     * Gets the class name
     *
     * @return The class name
     */
    public String getClassName() {
        return classname;
    }

    /**
     * Sets the class name
     *
     * @param name The class name
     */
    public void setClassName(String name) {
        this.classname = name;
    }

    /**
     * Instantiates the class referenced by class name and calls the greeting
     * method.
     */
    @Override
    public void output() {
        super.output();
        try {
            IGreeting ih = (IGreeting)Class.forName(classname).newInstance();
            Output.println("Instantiating class: '" + classname + "'");
            ih.greeting("World!");
        } catch (Exception e) {
            Output.println("Error finding class: " + classname);
        }
    }

    private String classname;
}