/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Class to handle the output from the main application and the beans.
 */
public class Output {

    /**
     * Sets the listener
     *
     * @param listener The listener
     */
    public static void setListener(PrintListener listener) {
        Output.listener = listener;
    }

    /**
     * Outputs a line
     *
     * @param s The line to output
     */
    public static void println(String s) {
        s = Output.tabs + s;
        System.out.println(s);
        sendToListener(s + "\n");
    }

    /**
     * Outputs an empty line
     */
    public static void println() {
        System.out.println();
        sendToListener("\n");
    }

    /**
     * Outputs a line of stars
     */
    public static void printStarLine() {
        Output.printStars("");
        println();
    }

    /**
     * Outputs a line of stars with text inside
     *
     * @param txt The text
     */
    public static void printStars(String txt) {
        int size = 75 - txt.length();
        StringBuffer sb = new StringBuffer("*****");
        if (size < 75) {
            sb.append(' ').append(txt).append(' ');
            size -= 2;
        }
        for (int i = 0; i < size; i++) {
            sb.append('*');
        }
        println(sb.toString());

    }

    /**
     * Resets the output
     */
    public static void reset() {
        Output.tabs = "";
    }

    /**
     * Outputs a step (and starts to indent)
     *
     * @param name The step nane
     */
    public static void step(String name) {
        Output.tabs = "";
        println(name);
        Output.tabs = "\t";
    }

    /**
     * Outputs the contents of an XML file.
     *
     * @param fileName The file name.
     */
    public static void printXMLFile(String fileName) {
        StringBuffer sb = new StringBuffer();
        Output.printStars("Bean XML File: " + fileName);
        Output.println();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    Output.class.getResourceAsStream("/" + fileName), "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                line = line.replaceAll("\t", "     ");
                sb.append(line).append("\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            Output.printError(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                Output.printError(e);
            }
        }
        println(sb.toString());
        Output.printStarLine();
    }

    /**
     * Prints the called method.
     *
     * @param className The class name
     */
    public static void printCalledMethod(String className) {
        println(className + "." + Output.getCallingMethodName() + "() called.");
    }

    /**
     * Gets the name of the calling method()
     *
     * @return The name of the method which called this method.
     */
    public static String getCallingMethodName() {
        StackTraceElement ste[] = Thread.currentThread().getStackTrace();
        if (ste.length > 4) {
            return ste[3].getMethodName();
        }
        return "unknown";
    }

    /**
     * Prints an exception
     *
     * @param e The exception
     */
    public static void printError(Throwable e) {
        e.printStackTrace();
        tabs = "";
        Output.printStars("ERROR OCCURRED");
        Output.println("An error occurred: " + e.getClass().getName() + " : " + e.getMessage());
        Output.printStarLine();
    }


    /**
     * Prints out the timing to the nearest 100th of a second.
     *
     * @param label The label
     * @param start The time it started.
     */
    public static void printTime(String label, long start) {
        long end = System.currentTimeMillis();
        Output.println(label + ": " + nf.format((end - start) / 1000d));
    }

    /**
     * Sends a printed line to a listener.
     *
     * @param line The listener
     */
    private static void sendToListener(String line) {
        if (listener != null) {
            listener.linePrinted(line);
        }
    }

    private static PrintListener listener;
    private static String tabs = "";
    private static NumberFormat nf = NumberFormat.getNumberInstance();

    static {
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
    }
}
