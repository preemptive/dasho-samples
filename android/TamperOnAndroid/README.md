# Tamper Check use in an Android App

This [sample Android&trade; app](../README.md#sample_desc) illustrates the use of `TamperCheck` and `TamperResponse` in _PreEmptive Protection - DashO_.
This project can be imported into Android Studio.
Obfuscation and injection are handled via the [DashO Gradle](../../docs/gradle/index.html) integration.

This sample is preconfigured with a single tamper check and three tamper responses.
It is configured in such a way that a debug build will act as if it was tampered and a release build will act in a non-tampered fashion.

The `TamperCheck` was placed on an internal method, `someApplicationLogic()`, called by the `MainActivity` class during startup.
This method returns a boolean value that will be `true` if the application has been tampered.
The value from that method is returned by the static `MainActivity.isInitialized()` method that is used as the source for the `TamperResponse` instances.

One `TamperResponse` was placed on the `doInBackground()` method used when calculating the Fibonacci sequence.

Another `TamperResponse` was placed on the `findRnd()` method used when calculating the random number.

Both of those responses use randomness to determine if they should or should not do anything.
There is also a programmatic use of the boolean variable set by the `TamperCheck` that shows a message to the user that the application has been tampered.

Feel free to reconfigure the probability and/or responses of the `TamperResponse` by editing them inside the `project.dox` file or via the DashO UI.

This sample includes a keystore, `keystore.ks`, with two certificates, `correct_cert` and `other_cert`.
The `correct_cert` is configured to be used when signing the release build.
The `TamperCheck` overrides the signing information passed by the Gradle integration preventing the correct certificate from being used during the debug build.
This is what allows this sample to easily show tampered behavior.  

>**Note:** In a real environment you should use the signing information passed to DashO by the Gradle integrations and not override it.

## Setup

See the main [README](../README.md) for the requirements.

>**Note:** When compiling, you may notice a `Warning` regarding `exit` on Android.
>The `exit` action on Android will only close the `Activity` on the top of the activity stack.

## Run Non-Tampered

Compile, obfuscate, and install the release (non-tampered) version of the application.

1.  Run the command: `gradlew uninstallAll` _(if necessary)_
2.  Run the command: `gradlew installRelease`

Run the application on your device. You will notice it behaves as expected; no errors occur and the app is responsive.

## Run Tampered

Compile, obfuscate, and install the debug (tampered) version of the application.

1.  Run the command: `gradlew uninstallAll` _(if necessary)_
2.  Run the command: `gradlew installDebug`

Run the application on your device. You will notice it does not behave as expected; random errors occur during use.

## How to Add Tamper Checking to Your Android Application

Adding basic tamper checking is relatively simple.

1.  Decide when during the lifetime of your app you want to make the check.
2.  Add a `TamperCheck` with an action to take.
3.  Configure the signing information (if needed).

To test it; extract, repackage, and resign your apk with a key that is not the original.

The keystore in this sample has two certificates.
You can see them by running: `keytool -list -v -keystore keystore.ks -storepass password`.
If `correct_cert` is used to when injecting tamper detection, and the installed APK has been signed with `other_cert`, the tamper check will show that the application has been tampered with.

If you wish to use `TamperResponse`, it is a bit more difficult as you must provide a mechanism for communication between the `TamperCheck` and `TamperResponse`.
In this sample, that mechanism involves the boolean variable and the `MainActivity.isInitialized()` method.

The tamper checks and responses may be added to the code directly as annotations or configured on the Tamper Check screen.

## Best Practices

Do not place the `TamperCheck` directly in the entry classes.
Hackers investigate those places first.
In this sample the `TamperCheck` was placed in an internal class that is called when the application starts up.
In a real application this should be an existing class and not one added for the sole purpose of tamper checking.
The `Responses` were added to different methods with different outcomes, randomly deciding if or if not to act on the result of the tamper check.
This makes it difficult to track down what is going wrong.

>**Note:** The Android robot is reproduced or modified from work created and shared by Google and used according to terms described in the [Creative Commons 3.0 Attribution License](http://creativecommons.org/licenses/by/3.0/).  
Android is a trademark of Google Inc.  
Gradle is a trademark of Gradle Inc.

Copyright 2018 [PreEmptive Solutions, LLC.](https://www.preemptive.com)
