# Android&trade; Samples

## Basic Requirements

* Java 1.8
* Android 8.1 (api 27) SDK platform
* Android SDK Build tools v27.0.3

>**Note:** The Android-specific requirements can be changed by editing the `build.gradle` file of the particular sample.

## Environment Setup

Before running these samples, you need to know where both the Android SDK and DashO are located.
If you import a sample into Android Studio, you should make sure Instant Run is disabled so the sample will work correctly.

### Android SDK

Set `sdk.dir` in `local.properties` to the location of the Android SDK.
It can also be set via an [environment variable](https://developer.android.com/studio/command-line/variables.html).

>**Note:** You will need to create the `local.properties` file.
>It should exist in the same directory as the `gradle.properties` file.

### DashO

Set `DASHO_HOME` in `gradle.properties` to the [location](https://www.preemptive.com/dasho/pro/userguide/en/getting_started_first.html#install_dir) of DashO.
It can also be passed via the command line: `-PDASHO_HOME={DashO's Installation Directory}`, or set via an environment variable named `ORG_GRADLE_PROJECT_DASHO_HOME`.

## Samples

* [DashO-GameOfLife](https://github.com/preemptive/DashO-GameOfLife) - A separate repository with a more-complex Android application that includes a library.
* [DebuggingOnAndroid](DebuggingOnAndroid) - An Android application which uses Debug Checking.
* [KotlinOnAndroid](KotlinOnAndroid) - An Android application using Kotlin.
* [RootOnAndroid](RootOnAndroid) - An Android application which uses Root Checking.
* [TamperOnAndroid](TamperOnAndroid) - An Android application which uses Tamper Checking.

Copyright 2018 [PreEmptive Solutions, LLC.](https://www.preemptive.com)

Android is a trademark of Google Inc.