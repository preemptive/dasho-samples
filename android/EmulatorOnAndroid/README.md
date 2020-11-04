# Emulator Check Use in an Android App

This [sample app](../README.md#sample_desc) for Android™ illustrates the use of `EmulatorCheck` and `EmulatorResponse` in _PreEmptive Protection™ DashO™_.
This project can be imported into Android Studio.
[Control Flow Obfuscation](https://www.preemptive.com/dasho/pro/userguide/en/understanding_obfuscation_control.html), [String Encryption](https://www.preemptive.com/dasho/pro/userguide/en/understanding_obfuscation_string_encryption.html), and [Check Injection](https://www.preemptive.com/dasho/pro/userguide/en/understanding_checks_overview.html) are handled via the [DashO Gradle Plugin for Android](https://www.preemptive.com/dasho/pro/userguide/en/ref_dagp_index.html) integration.
Renaming Obfuscation and Removal are handled by [R8](https://r8-docs.preemptive.com/).

This sample is preconfigured with a single Emulator Check and two Emulator Responses.
It is configured in such a way that emulator detection is injected into the release build and not the debug build.

The `EmulatorCheck` was placed on an internal method, `someApplicationLogic()`, called by the `MainActivity` class during startup.
This method returns a boolean value that will be `true` if the application is running on an emulator.
The value from that method is returned by the static `MainActivity.isInitialized()` method that is used as the source for the `EmulatorResponse` instances.

One `EmulatorResponse` was placed on the `find()` method used when calculating the Fibonacci sequence.
Another `EmulatorResponse` was placed on the `findRnd()` method used when calculating the random number.
Both of those Responses use randomness to determine if they should or should not do anything.

There is also a programmatic use of the boolean variable set by the `EmulatorCheck` that shows a message to the user that it is being run on an emulator.

Feel free to reconfigure the probability and/or responses of the `EmulatorResponse` by editing them inside the `project.dox` file or via the DashO UI.

This sample includes a keystore, `keystore.ks`, that is used to sign the release build.

## Setup

See the main [README](../README.md) for the requirements.

## Run without Emulator Check

Compile, protect, and install the debug version of the application.

1.  Run the command: `gradlew uninstallAll` _(if necessary)_
2.  Run the command: `gradlew installDebug`

Run the application on an emulator or device.
You will notice it behaves as expected; no errors occur and the app is responsive.

## Run with Emulator Check

Compile, protect, and install the release version of the application.

>**Note:** When compiling, you may notice a `Warning` regarding `exit` on Android. The `exit` action on Android will only close the `Activity` on the top of the activity stack.

1.  Run the command: `gradlew uninstallAll` _(if necessary)_
2.  Run the command: `gradlew installRelease`

Run the application on an emulator.
You will notice it does not behave as expected on an emulator; random errors occur during use.
It behaves normally on a device.

## How to Add Emulator Checking to Your Android Application

Adding basic Emulator Checks is relatively simple.

1.  Decide when during the lifetime of your app you want to make the Check.
2.  Add an `EmulatorCheck` with an action to take.

To test it; run the app on an emulator.

If you wish to use `EmulatorResponse`, it is a bit more difficult as you must provide a mechanism for communication between the `EmulatorCheck` and `EmulatorResponse`.
In this sample, that mechanism involves the boolean variable and the `MainActivity.isInitialized()` method.

The Emulator Checks and Responses may be added to the code directly as annotations or configured on the Emulator Check screen.

## Best Practices

Do not place the `EmulatorCheck` directly in the entry classes.
Hackers investigate those places first.
In this sample the `EmulatorCheck` was placed in an internal class that is called when the application starts up.
In a real application this should be an existing class and not one added for the sole purpose of doing Emulator Checks.
The Responses were added to different methods with different outcomes, randomly deciding whether or not to act on the result of the Emulator Check.
This makes it difficult for an attacker to track down what is going wrong.

>**Note:** The Android robot is reproduced or modified from work created and shared by Google and used according to terms described in the [Creative Commons 3.0 Attribution License](http://creativecommons.org/licenses/by/3.0/).
><br>Android is a trademark of Google Inc.
><br>Gradle is a trademark of Gradle Inc.
