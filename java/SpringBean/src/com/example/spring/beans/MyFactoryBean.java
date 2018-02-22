/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.beans;

import com.example.spring.util.Output;

/**
 * A bean to show life cycle. Methods print their name when called.
 */
public class MyFactoryBean extends OutputBean {
    public MyFactoryBean(String ID) {
        super();
    }

    public void my_init() {
        outputCalledMethod();
    }

    public void my_init2() {
        outputCalledMethod();
    }

    public void my_init3() {
        outputCalledMethod();
    }

    public void my_destroy() {
        outputCalledMethod();
    }

    public void my_destroy2() {
        outputCalledMethod();
    }

    public void my_destroy3() {
        outputCalledMethod();
    }

    public static MyFactoryBean my_factory() {
        Output.printCalledMethod(internalClassName);
        return new MyFactoryBean("1");
    }

    public static MyFactoryBean my_factory2() {
        Output.printCalledMethod(internalClassName);
        return new MyFactoryBean("2");
    }

    public static MyFactoryBean my_factory3() {
        Output.printCalledMethod(internalClassName);
        return new MyFactoryBean("3");
    }

    private static final String internalClassName;

    static {
        internalClassName = MyFactoryBean.class.getName();
    }

}
