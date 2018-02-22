/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package dasho.samples;

public class HelloWorld {
    private static String name;

    public static void main(final String[] args){
    if(args.length == 0){
        name = "no name";
    }else{
        name = args[0];
    }
    final Greetings greetings = new Greetings();
    greetings.sayGreetings(name);
    greetings.sayHello(name);
    greetings.sayGoodbye(name);
    }

}