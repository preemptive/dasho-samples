/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

import com.preemptive.annotation.instrumentation.ShelfLifeCheck;
import com.preemptive.instrumentation.Token;

/**
 * Sample of using Shelf Life to authorize the use of an application. In addition, if there is no token to authorize the
 * application then a 15 day free trial begins.
 * <p>
 * Illustrates the use of:
 * <ul>
 * <li>Using the ExpiryTokenSource annotation to get a token that is not stored in the application.</li>
 * <li>Using a ShelfLifeCheck annotation that calls a method in another class.</li>
 * <li>Creating a relative expiration date with annotations.</li>
 * </ul>
 * The <code>main()</code> can be used to restart the trial period.
 * 
 * @author PreEmptive Solutions
 */
public class MetalworksAuthorization {
    private static final String START_DATE_KEY = "MetalworksFreeTrialStart";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Restarts the start date used for the free trial.
     * <p>
     * Note: DashO will need this as an entry point. If you want to remove the reset feature, simply remove this method
     * as an entry point.
     * 
     * @param args resets the value if the first argument is reset
     */
    public static void main(final String[] args) {
        if(args.length == 1 && args[0].equalsIgnoreCase("reset")){
            System.out.println(args[0]);
            Preferences.userRoot().remove(START_DATE_KEY);
        }
    }

    /**
     * Returns a Reader that gets the token. It looks for a <i>Metalworks.token</i> in the current directory. This
     * method is invoked by an ExpiryTokenSource annotation in the application.
     * <p>
     * The token can be returned from any source, for example a web service that uses some information about the user or
     * machine to provide their authorization token.
     * 
     * @return a Reader for the token or <code>null</code>.
     */
    public static Reader getToken() {
        try {
            return new FileReader("Metalworks.token");
        } catch(FileNotFoundException exception) {
            System.out.println("Metalworks.token not found");
            return null;
        } catch(Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Determine if we have a token which indicates we are authorized. If the token is
     * <code>null</node> the token file is missing or corrupt and we check to see if the user is in a free trial period.
     * This method is invoked by a ShelfLifeCheck annotation in the application.
     * 
     * @param token the token, which may be null.
     */
    public static void check(final Token token) {
        if(token == null){
            checkInFreeTrial();
        }else{
            // At this point you can retrieve specific information from the authorization token
            // by using token.getProperties(). These values can be used to store information specific to the licensee
            // or other information that ties the authorization token to a specific machine.
        }
    }

    /**
     * A hook point for instrumentation. We get the start date for our 15 day free trial period. DashO will add all the
     * detection logic and handle the expiration and warning actions.
     * <p><b>Note:</b><br>
     * When relative paths appear in annoations DashO interprets them relative to the <b><i>project file</i></b> not
     * relative to the source file in which they appear.  
     * </p>
     */
    @ShelfLifeCheck(startDateSource = "@getStartDate()", expirationPeriod = "15", warningPeriod = "5", sendMessage = false)
    private static void checkInFreeTrial() {
        // All actions are added by DashO
    }

    /**
     * Returns the date when the free trial period started. It also pops up a dialog when the free trial period starts.
     * 
     * @return The start date for the free trial period.
     */
    private static Date getStartDate() {
        Date startDate = null;
        final String t = Preferences.userRoot().get(START_DATE_KEY, null);
        if(t == null){
            startDate = new Date();
            Preferences.userRoot().put(START_DATE_KEY, DATE_FORMAT.format(startDate));
            showMessage("Free Trial!", "Thanks for trying Metalworks.");
        }else{
            try{
                startDate = DATE_FORMAT.parse(t);
            }
            catch(ParseException e){
                e.printStackTrace();
            }
        }
        return startDate;
    }

    private static void showMessage(String title, String message) {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println(title + "  " + message);
        } else {
            final Frame[] frames = Frame.getFrames();
            Frame parentFrame = frames.length > 0 ? frames[0] : null;
            JOptionPane.showMessageDialog(parentFrame, message, title,
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
