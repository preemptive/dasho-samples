/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package dasho.samples;

public class Main {
    public static void main(final String[] args) {
        if (args.length == 0) {
            System.out.println("Hello World");
        } else {
            System.out.println("Hello " + args[0]);
        }
    }
}