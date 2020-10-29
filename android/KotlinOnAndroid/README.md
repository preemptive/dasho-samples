# Kotlin™ use in an Android™ App

This [sample app](../README.md#sample_desc) for Android™ illustrates the use of Kotlin™ with _PreEmptive Protection™ DashO™_.
This project can be imported into Android Studio.
[Control Flow Obfuscation](https://www.preemptive.com/dasho/pro/userguide/en/understanding_obfuscation_control.html), [String Encryption](https://www.preemptive.com/dasho/pro/userguide/en/understanding_obfuscation_string_encryption.html), and [Check Injection](https://www.preemptive.com/dasho/pro/userguide/en/understanding_checks_overview.html) are handled via the [DashO Gradle Plugin for Android](https://www.preemptive.com/dasho/pro/userguide/en/ref_dagp_index.html) integration.
Renaming Obfuscation and Removal are handled by [R8](https://r8-docs.preemptive.com/).

This sample uses the Gradle Kotlin DSL and is preconfigured with protection.

>**Note:** The `kotlinx.coroutines.internal.MainDispatcherLoader.loadMainDispatcher()` method has been excluded from control flow processing to work around a bug in R8.

## Setup

See the main [README](../README.md) for the requirements.

## Running

Compile, protect, and install a debug version of the application.

1.  Run the command: `gradlew clean installDebug`

>**Note:** You may notice some `Skipped duplicate entry` warnings.
>Those can be ignored.

Run the application on your device or emulator.

>**Note:** The Android robot is reproduced or modified from work created and shared by Google and used according to terms described in the [Creative Commons 3.0 Attribution License](http://creativecommons.org/licenses/by/3.0/).
><br>Android is a trademark of Google Inc.
><br>Kotlin is a trademark of the Kotlin Foundation.
><br>Gradle is a trademark of Gradle Inc.
