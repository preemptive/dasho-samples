# Shelf Life - Authorized Application Example

## Overview

This example uses Shelf Life to add an authorization check to an existing application.
In this case the application is Metalworks, one of the demo programs that is available with the Oracle JDK.
The example uses a combination of code instrumented by _PreEmptive Protection™ DashO™ for Android & Java_ and some custom code in a class that is combined with the Metalworks jar.

The class `MetalworksAuthorization` contains static methods that are called by the added Shelf Life code.
Configuration in the DashO project file adds a Shelf Life check to the constructor of `MetalworksFrame`, and is equivalent to:

    @ShelfLifeCheck(tokenSource = "MetalworksAuthorization.getToken()", action = "MetalworksAuthorization.check()", where = InjectionPoint.End)

The instrumented code will call the `getToken()` to try and read the Shelf Life token that is used as an authorization file.
Its presence is just used as a key to allow the application to run.
This token, or `null`, is then passed to the `check()` method.

    public static void check(final Token token) {
        if(token == null){
            checkInFreeTrial();
        }else{
            // application is authorized
        }
    }

When the `token` argument passed to this method is `null` either the authorization file is missing or is not a valid Shelf Life token.
In that case, the `checkInFreeTrial()` method is called.
In the source code this method has an empty body - all of its logic is added by DashO:

    @ShelfLifeCheck(startDateSource = "@getStartDate()", expirationPeriod = "15", warningPeriod = "5")
    private static void checkInFreeTrial() {
        // All actions are added by DashO
    }

The final part is the `getStartDate()` method referenced as the source for the start date.
This method uses the `java.util.prefs.Preferences` to store and retrieve the start date for the free trial period.
When the start date is not found in the Preferences it is set to the current date and a dialog is presented.

## Setup

See the main [README](../README.md) for the requirements.

## Compiling and Obfuscating

1.  Copy your Shelf Life key file into the parent of the example's directory and make sure it is named `sample.slkey`.
2.  Download the demos and samples package corresponding to your JDK if you do not already have it. Copy the `Metalworks.jar` from the `demo/jfc/Metalworks` directory into the example's directory.
3.  Compile the source `ant compile`. Two jars from DashO are needed to compile `MetalworksAuthorization` since it uses DashO annotations and the `Token` class. These jars are _not_ required at runtime.
4.  Instrument and obfuscate using DashO. You can either run the `project.dox` from DashO's user interface or run `ant instrument`.

The resulting `ProtectedMetalworks.jar` is now ready for testing.


## Testing

1.  Run the jar without it's authorization token.

        java -jar ProtectedMetalworks.jar

    The first time it is run it will bring up a _Free Trial_ dialog.
    After the initial run it will remain silent until the expiration warning period starts.
2.  Create a `Metalworks.token` to authorize the application.
    You can do this from the user interface using the dialog under the _Window→Shelf Life Token_ item.
    You can also use the command line script:

        tokengenerator --key ..\sample.slkey --date 01/01/2020 --file Metalworks.token

    In this sample, the token is only checked for being valid, not expired, so the expiration date used does not matter.
3.  Reset the starting date by running

        java -classpath ProtectedMetalworks.jar MetalworksAuthorization reset

4.  Run the jar again - no dialogs should be shown since the application is now authorized.
