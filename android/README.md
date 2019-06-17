# Android&trade; Samples

## Basic Requirements

* Java 1.8
* Android 9.0 (api 28) SDK platform

>**Note:** The Android-specific requirements can be changed by editing the `build.gradle` file of the particular sample.

## Environment Setup

Before running these samples, they need to know where both the Android SDK and DashO are located.
>**Note:** If you import a sample into Android Studio, you should make sure Instant Run is [disabled](https://developer.android.com/studio/run/#disable-ir) so the sample will work correctly.

### Android SDK

Set `sdk.dir` in `local.properties` to the location of the Android SDK.
It can also be set via an [environment variable](https://developer.android.com/studio/command-line/variables.html).

>**Note:** You will need to create the `local.properties` file.
>It should exist in the same directory as the `gradle.properties` file.

### DashO

If DashO is installed in the default location, the *DashO Gradle Plugin for Android* will find *DashO* automatically.
Otherwise, you will need to set `DASHO_HOME` in `gradle.properties` to the DashO home directory.
It can also be passed via the command line: `-PDASHO_HOME=<DashO Home>`, or set via an environment variable named `ORG_GRADLE_PROJECT_DASHO_HOME`.
See the [DashO User Guide](https://www.preemptive.com/dasho/pro/10.0/userguide/en/ref_dagp_dasho_home.html) for details.

## Samples

* [DashO-GameOfLife](https://github.com/preemptive/DashO-GameOfLife) - A separate repository with a more-complex Android application that includes a library.
* [DebuggingOnAndroid](DebuggingOnAndroid) - An Android application which uses Debug Checking.
* [EmulatorOnAndroid](EmulatorOnAndroid) - An Android application which uses Emulator Checking.
* [HookingOnAndroid](HookingOnAndroid) - An Android application which uses Hook Checking.
* [KotlinOnAndroid](KotlinOnAndroid) - An Android application using Kotlin.
* [RootOnAndroid](RootOnAndroid) - An Android application which uses Root Checking.
* [TamperOnAndroid](TamperOnAndroid) - An Android application which uses Tamper Checking.

<a name="sample_desc"></a>
### Running the Samples

The included samples have the same basic functionality.

![sample](sample.png)

The main screen allows selection of the *Random Generator* or the *Fibonacci Calculator*.
The *Random Generator* generates and displays a random number in a configured range.
The*Fibonacci Calculator*  calculates and displays a number in the Fibonacci sequence.
The samples for Checks and Responses attach additional behavior to the `Generate` and `Calculate` actions.
When the Check is triggered, those areas of the application will not work properly.



Copyright 2019 [PreEmptive Solutions, LLC.](https://www.preemptive.com)

Android is a trademark of Google Inc.
