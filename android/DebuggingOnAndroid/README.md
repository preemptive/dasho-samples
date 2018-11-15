# Debug Checks use in an Android App

This [sample Android&trade; app](../README.md#sample_desc) illustrates the use of the debug checks and responses in _PreEmptive Protection - DashO_.
This project can be imported into Android Studio.
Obfuscation and injection are handled via the [DashO Gradle](../../docs/gradle/index.html) integration.

This sample is preconfigured with both debug enabled and debugging checks and responses.

The `DebugEnabledCheck` was placed on an internal method, `someApplicationLogic()`, called by the `MainActivity` class during startup.
This method returns a boolean value that will be `true` if the application appears to be debuggable.
The value from that method is returned by the static `MainActivity.isInitialized()` method that is used as the source for the `DebugEnabledResponse`.

The `DebugEnabledResponse` was placed on the `findRnd()` method used when calculating the random number.

The `DebuggingCheck` was placed on the `onCreate()` method in the `FibonacciActivity` class.
It sets the static variable `check` that is used by the `DebuggingResponse`.

The `DebuggingResponse` was placed on the `doInBackground()` method used when calculating the Fibonacci sequence.

Both of the responses use randomness to determine if they should or should not do anything.
There is also programmatic use of the boolean variables set by the `DebugEnabledCheck` and `DebugggingCheck` that shows a message to the user when launching the features.

Feel free to reconfigure the probability and/or response types of the responses by editing them in the `project.dox` file.

## Setup

See the main [README](../README.md) for the requirements.

>**Note:** When compiling, you may notice a `Warning` regarding `exit` on Android.
>The `exit` action on Android will only close the `Activity` on the top of the activity stack.

## Run Normal

Compile, obfuscate, and install the release (non-debugging) version of the application.

1.  Run the command: `gradlew uninstallAll` _(if necessary)_
2.  Run the command: `gradlew installRelease`

>**Note:** If USB debugging is enabled, then the `DebugEnabledCheck` will trigger.
>USB debugging is required to deploy the application to a real device, and some emulators have USB debugging enabled by default.
>Disable USB debugging in [Developer options](https://developer.android.com/studio/debug/dev-options) before running the application if it is enabled.

Run the application on your device.
You will notice it behaves as expected; no errors occur and the app is responsive.

## Run with Debugging Enabled

Compile, obfuscate, and install the debug version of the application.

1.  Re-enable USB debugging _(if necessary)_
2.  Run the command: `gradlew uninstallAll` _(if necessary)_
3.  Run the command: `gradlew installDebug`

Run the application on your device.
You will notice that the Random Number feature indicates debugging is enabled, but the Fibonacci feature indicates it is not being debugged.
The Random Number feature will fail sometimes because of the `DebugEnabledResponse`.

## Actively Debug

Compile, obfuscate, and install the debug version of the application.  

>**Note:** These instructions assume `adb` and `jdb` are available via your `PATH` environment variable.

1.  Run the command: `gradlew uninstallAll` _(if necessary)_
2.  Run the command: `gradlew installDebug`

Attach the debugger.

1.  Run the command: `adb shell`
2.  Run the command: `am start -D -n com.dasho.android.debug/com.dasho.android.debug.MainActivity`
3.  Run the command: `exit`
4.  Run the command: `adb jdwp` (look at the last process id then press `Ctrl`+`C`)
5.  Run the command: `adb forward tcp:8021 jdwp:_{the last process id}_`
6.  Run the command: `jdb -connect com.sun.jdi.SocketAttach:hostname=localhost,port=8021`

Interact with the application on your device.
You will notice that the Random Number feature indicates debugging is enabled and the Fibonacci feature indicates it is being debugged.
Both features will fail sometimes because of the configured `Responses`.

>**Note:** If Android Studio is running, you will need to stop it in order for `jdb` to be able to connect to the process.

## How to Add Debug Checking to Your Android Application

Adding basic debug checking is relatively simple.

1.  Decide when during the lifetime of your app you want to make the check.
2.  Add a `DebugEnabledCheck` or `DebuggingCheck` along with an action to take.

If you wish to use `DebugEnabledResponse` or `DebuggingResponse`, it is a bit more difficult as you must provide a mechanism for communication between the `Check` and `Response`.
In this sample, one of the mechanisms involves the boolean variable and the `MainActivity.isInitialized()` method.

These debug checks and responses may be added to the code directly as annotations or configured on the Debug Check screen.

## Best Practices

Do not place the `DebuggingCheck` or `DebugEnabledCheck` directly in the entry classes.
Hackers investigate those places first.
In this sample the `DebugEnabledCheck` was placed in an internal class that is called when the application starts up.
In a real application this should be an existing class and not one added for the sole purpose of debug checking.
The `Responses` were added to different methods with different outcomes, randomly deciding if or if not to act on the result from the debug check.
This makes it difficult to track down what is going wrong.

You can also run your own custom response by referencing a method in the check's `action`.
If called with `true`, the check was positive for debugging and you can perform a custom action like sending a message to Google Analytics.

>**Note:** The Android robot is reproduced or modified from work created and shared by Google and used according to terms described in the [Creative Commons 3.0 Attribution License](http://creativecommons.org/licenses/by/3.0/).  
Android is a trademark of Google Inc.  
Gradle is a trademark of Gradle Inc.

Copyright 2018 [PreEmptive Solutions, LLC.](https://www.preemptive.com)
