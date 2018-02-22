/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package dasho.samples;

import java.awt.GraphicsEnvironment;
import java.text.DateFormat;

import javax.swing.JOptionPane;

import com.preemptive.instrumentation.Token;

/**
 * This class contains a static method for processing the Shelf Life token. DashO will inject the
 * call to this method as the 'action' of the ShelfLifeCheck. With this approach, the same jar file
 * can be protected with Shelf Life or not, depending on whether it has been processed with DashO.
 */
class Checker {
    private static DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.LONG);

    static void check(final Token token) {
        // If the token has be tampered with you can get a null
        if (token == null) {
            showMessage("Expired", "The application has expired", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        if (token.isExpired()) {
            String message
                = "The application expired on " + DATE_FORMAT.format(token.getExpirationDate());
            showMessage("Expired", message, JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        if (token.isInWarning()) {
            String message
                = "The application will expire in " + token.getDaysTillExpiration() + " days";
            showMessage("Expiration Warning", message, JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void showMessage(String title, String message, int messageType) {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println(title + "  " + message);
        } else {
            JOptionPane.showMessageDialog(null, message, title, messageType);
        }
    }
}
