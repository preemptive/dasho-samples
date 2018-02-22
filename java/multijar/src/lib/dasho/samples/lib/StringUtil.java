/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package dasho.samples.lib;

public class StringUtil {
    public static String reverseWords(final String in) {
        if(isEmpty(in)){
            return in;
        }
        final String[] words = in.split(" ");
        final StringBuffer sb = new StringBuffer();
        for(int i = words.length - 1; i >= 0; i--){
            if(sb.length() > 0) sb.append(' ');
            sb.append(words[i]);
        }
        return sb.toString();
    }

    public static String concat(final String[] values) {
        if(values == null || values.length == 0){
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        for(int i = 0; i < values.length; i++){
            if(sb.length() > 0) sb.append(' ');
            if(!isEmpty(values[i])){
                sb.append('[').append(values[i]).append(']');
            }
        }
        return sb.toString();
    }

    public static boolean isEmpty(final String value) {
        return value == null || value.length() == 0;
    }
    
    public static boolean isBlank(final String value) {
        return value == null || isEmpty(value.trim());
    }
}
