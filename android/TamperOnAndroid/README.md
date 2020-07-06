# Tamper Check use in an Android App

This [sample app](../README.md#sample_desc) for Android™ illustrates the use of `TamperCheck` and `TamperResponse` in _PreEmptive Protection™ DashO™_.
This project can be imported into Android Studio.
[Control Flow Obfuscation](https://www.preemptive.com/dasho/pro/userguide/en/understanding_obfuscation_control.html), [String Encryption](https://www.preemptive.com/dasho/pro/userguide/en/understanding_obfuscation_string_encryption.html), and [Check Injection](https://www.preemptive.com/dasho/pro/userguide/en/understanding_checks_overview.html) are handled via the [DashO Gradle Plugin for Android](https://www.preemptive.com/dasho/pro/userguide/en/ref_dagp_index.html) integration.
Renaming Obfuscation and Removal are handled by [R8](https://r8-docs.preemptive.com/).

This sample is preconfigured with a single Tamper Check and two Tamper Responses.
It is configured in such a way that a debug build will act as if it was tampered and a release build will act in a non-tampered fashion.

The `TamperCheck` was placed on an internal method, `someApplicationLogic()`, called by the `MainActivity` class during startup.
This method returns a boolean value that will be `true` if the application has been tampered.
The value from that method is returned by the static `MainActivity.isInitialized()` method that is used as the source for the `TamperResponse` instances.

One `TamperResponse` was placed on the `doInBackground()` method used when calculating the Fibonacci sequence.
Another `TamperResponse` was placed on the `findRnd()` method used when calculating the random number.
Both of those Responses use randomness to determine if they should or should not do anything.

There is also a programmatic use of the boolean variable set by the `TamperCheck` that shows a message to the user that the application has been tampered.

Feel free to reconfigure the probability and/or responses of the `TamperResponse` by editing them inside the `project.dox` file or via the DashO UI.

This sample includes a keystore, `keystore.ks`, with two certificates, `correct_cert` and `other_cert`.
The `correct_cert` is configured to be used when signing the release build.
The `TamperCheck` overrides the signing information passed by the Gradle integration preventing the correct certificate from being used during the debug build.
This is what allows this sample to easily show tampered behavior.

>**Note:**
> In general, real applications should use the signing information passed to *DashO* by the *DashO Gradle Plugin for Android* and not override it.
> This is not the case when using [Google Play App Signing](https://developer.android.com/studio/publish/app-signing.html#app-signing-google-play).
> See the [DashO User Guide](https://www.preemptive.com/dasho/pro/userguide/en/understanding_checks_tamper.html#google_play_signing) for details.

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

Adding basic Tamper Checks is relatively simple.

1.  Decide when during the lifetime of your app you want to make the Check.
2.  Add a `TamperCheck` with an action to take.
3.  Configure the signing information (if needed).

To test it; extract, repackage, and resign your apk with a key that is not the original.

The keystore in this sample has two certificates.
You can see them by running: `keytool -list -v -keystore keystore.ks -storepass password`.
If `correct_cert` is used to when injecting tamper detection, and the installed APK has been signed with `other_cert`, the Tamper Check will show that the application has been tampered with.

If you wish to use `TamperResponse`, it is a bit more difficult as you must provide a mechanism for communication between the `TamperCheck` and `TamperResponse`.
In this sample, that mechanism involves the boolean variable and the `MainActivity.isInitialized()` method.

The Tamper Checks and Responses may be added to the code directly as annotations or configured on the Tamper Check screen.

## Best Practices

Do not place the `TamperCheck` directly in the entry classes.
Hackers investigate those places first.
In this sample the `TamperCheck` was placed in an internal class that is called when the application starts up.
In a real application this should be an existing class and not one added for the sole purpose of Tamper Checks.
The Responses were added to different methods with different outcomes, randomly deciding whether or not to act on the result of the Tamper Check.
This makes it difficult for an attacker to track down what is going wrong.

>**Note:** The Android robot is reproduced or modified from work created and shared by Google and used according to terms described in the [Creative Commons 3.0 Attribution License](http://creativecommons.org/licenses/by/3.0/).
Android is a trademark of Google Inc.
Gradle is a trademark of Gradle Inc.
