/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.util;

/**
 * An interface to know when a line is printed.
 */
public interface PrintListener {

    /**
     * A line was printed
     *
     * @param line The printed line
     */
    void linePrinted(String line);
}
