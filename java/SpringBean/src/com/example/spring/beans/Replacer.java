/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.beans;

import com.example.spring.util.Output;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;
import org.springframework.beans.factory.support.MethodReplacer;

/**
 * The Method Replacer
 */
public class Replacer extends OutputBean implements MethodReplacer {

    /**
     * This is the method replacing calls to printTimeBean.
     */
    public Object reimplement(Object o, Method m, Object[] a) throws Throwable {
        outputCalledMethod();
        TimeBean tb = (TimeBean)(a[0]);
        if (tb != null) {
            int id = tb.getCounter();
            long time = tb.getTime();
            Output.println("TimeBean ID #" + id + " was created at: " + df.format(new Date(time)));
        } else {
            Output.println("Bean was null");
        }
        return null;
    }

    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG);
}