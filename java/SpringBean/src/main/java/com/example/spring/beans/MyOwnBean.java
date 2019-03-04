/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.beans;

/**
 * A bean to show life cycle. Methods print their name when called.
 */
public class MyOwnBean extends OutputBean {

    public MyOwnBean() {
        super();
    }

    public void my_init() {
        outputCalledMethod();
        p_my_init();
    }

    private void p_my_init() {
        outputCalledMethod();
    }

    public void my_destroy() {
        outputCalledMethod();
        p_my_destroy();
    }

    private void p_my_destroy() {
        outputCalledMethod();
    }

    public void def_init() {
        outputCalledMethod();
        p_def_init();
    }

    private void p_def_init() {
        outputCalledMethod();
    }

    public void def_destroy() {
        outputCalledMethod();
        p_def_destroy();
    }

    private void p_def_destroy() {
        outputCalledMethod();
    }

}
