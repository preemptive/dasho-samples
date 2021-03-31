/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package dasho.samples;

@Inject
public class Greetings {


    @Inject
    public static String thing = "bruh";

    @Inject
    public void sayGreetings(final String name){
    System.out.println("Greetings " + name);
    }

    public void sayHello(final String name){
    System.out.println("Hello " + name);
    }

    public void sayGoodbye(final String name){
    System.out.println("Goodbye " + name);
    }

}

