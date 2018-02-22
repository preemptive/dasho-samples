/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.beans;

import com.example.spring.util.Output;

/**
 * A bean with a parent bean reference
 *
 * @author minsko
 */
public class ChildBean extends OutputBean {


    public int getNum() {
        outputCalledMethod();
        return num;
    }

    public void setNum(int num) {
        outputCalledMethod();
        this.num = num;
    }

    public String getStr() {
        outputCalledMethod();
        return str;
    }

    public void setStr(String str) {
        outputCalledMethod();
        this.str = str;
    }

    public float getFlt() {
        outputCalledMethod();
        return flt;
    }

    public void setFlt(float flt) {
        outputCalledMethod();
        this.flt = flt;
    }

    @Override
    public void output() {
        super.output();
        int n = getNum();
        Output.println("num: " + n);
        float f = getFlt();
        Output.println("flt: " + f);
        String s = getStr();
        Output.println("str: " + s);
    }

    private int num;
    private String str;
    private float flt;
}
