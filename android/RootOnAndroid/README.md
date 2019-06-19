# Root Check Use in an Android App

This [sample Android&trade; app](../README.md#sample_desc) illustrates the use of `RootCheck` and `RootResponse` in _PreEmptive Protection - DashO_.
This project can be imported into Android Studio.
Obfuscation and injection are handled via the [DashO Gradle Plugin for Android](https://www.preemptive.com/dasho/pro/10.0/userguide/en/ref_dagp_index.html) integration.
Renaming and removal are handled by [R8](https://r8-docs.preemptive.com/).

This sample is preconfigured with a single root check and three root responses.
It is configured in such a way that root detection is injected into the release build and not the debug build.

The `RootCheck` was placed on an internal method, `someApplicationLogic()`, called by the `MainActivity` class during startup.
This method returns a boolean value that will be `true` if the application is running on a rooted device.
The value from that method is returned by the static `MainActivity.isInitialized()` method that is used as the source for the `RootResponse` instances.

One `RootResponse` was placed on the `doInBackground()` method used when calculating the Fibonacci sequence.

Another `RootResponse` was placed on the `findRnd()` method used when calculating the random number.

Both of those responses use randomness to determine if they should or should not do anything.
There is also a programmatic use of the boolean variable set by the `RootCheck` that shows a message to the user that the device is rooted.

Feel free to reconfigure the probability and/or responses of the `RootResponse` by editing them inside the `project.dox` file or via the DashO UI.

This sample includes a keystore, `keystore.ks`, that is used to sign the release build.

## Setup

See the main [README](../README.md) for the requirements.

## Run without Root Check

Compile, obfuscate, and install the debug version of the application.

1.  Run the command: `gradlew uninstallAll` _(if necessary)_
2.  Run the command: `gradlew installDebug`

Run the application on an emulator, rooted device, or unrooted device.
You will notice it behaves as expected; no errors occur and the app is responsive.

## Run with Root Check

Compile, obfuscate, and install the release version of the application.

>**Note:** When compiling, you may notice a `Warning` regarding `exit` on Android. The `exit` action on Android will only close the `Activity` on the top of the activity stack.

1.  Run the command: `gradlew uninstallAll` _(if necessary)_
2.  Run the command: `gradlew installRelease`

Run the application on an emulator, rooted device, or unrooted device.
You will notice it does not behave as expected on a rooted device; random errors occur during use.
It behaves normally on an unrooted device.

>**Note:** Many emulators will also trigger the Root Check

## How to Add Root Checking to Your Android Application

Adding basic root checking is relatively simple.

1.  Decide when during the lifetime of your app you want to make the check.
2.  Add a `RootCheck` with an action to take.

To test it; run the app on a rooted device or emulator.

If you wish to use `RootResponse`, it is a bit more difficult as you must provide a mechanism for communication between the `RootCheck` and `RootResponse`.
In this sample, that mechanism involves the boolean variable and the `MainActivity.isInitialized()` method.

The root checks and responses may be added to the code directly as annotations or configured on the Root Check screen.

## Best Practices

Do not place the `RootCheck` directly in the entry classes.
Hackers investigate those places first.
In this sample the `RootCheck` was placed in an internal class that is called when the application starts up.
In a real application this should be an existing class and not one added for the sole purpose of root checking.
The `Responses` were added to different methods with different outcomes, randomly deciding if or if not to act on the result of the root check.
This makes it difficult to track down what is going wrong.

>**Note:** The Android robot is reproduced or modified from work created and shared by Google and used according to terms described in the [Creative Commons 3.0 Attribution License](http://creativecommons.org/licenses/by/3.0/).
Android is a trademark of Google Inc.
Gradle is a trademark of Gradle Inc.

Copyright 2019 [PreEmptive Solutions, LLC.](https://www.preemptive.com)
