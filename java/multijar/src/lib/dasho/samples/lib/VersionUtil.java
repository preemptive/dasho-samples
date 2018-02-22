/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package dasho.samples.lib;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class VersionUtil {
    private static final String MISSING_VALUE = "** MISSING **";

    public static void printVersion(final Class context, final String appName) {
        printVersion(readProperties(context, appName));
    }
    
    public static void printVersion(final Properties props) {
        System.out.print(props.getProperty("name", MISSING_VALUE));
        System.out.print(" V");
        System.out.println(props.getProperty("version", MISSING_VALUE));
    }
    
    public static Properties readProperties(final Class context, final String appName) {
        final Properties props = new Properties();
        final InputStream in = context.getResourceAsStream(appName + ".properties");
        if(in == null){
            throw new Error("Missing application properties for " + appName);
        }
        try {
            props.load(in);
        }
        catch(IOException e){
            throw new Error("Error reading application properties for " + appName);
        }
        return props;
    }

    public static String createVersion(final int major, final int minor) {
        return Integer.toString(major) + "." + Integer.toString(minor);
    }
    
}