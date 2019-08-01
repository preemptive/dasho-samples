# Spring Bean Handling

This sample illustrates how _PreEmptive Protection™ DashO™ for Android & Java_ works with Spring Beans.
It will be compiled via [Gradle](http://www.gradle.org), obfuscated with DashO, and then both the original and obfuscated versions of the application should be run side-by-side.

## Setup

See the main [README](../README.md) for the requirements.

## Building

These instructions will guide you through the process of building the original app using Gradle.

1. Open a command prompt (or shell).
2. Change to the `SpringBean` directory.
3. Run the command:`gradlew downloadJars` (This will download the jars needed to compile and run the application, placing them in the `libs` directory.)
4. Run the command: `gradlew build` (Make sure you do not have any compile errors and that `MySpringApp.jar` is created).
5. Run the command: `gradlew run` (Click on the buttons and verify no errors occur).
6. Close the app by clicking the `X`.

If any errors occurred during compiling or running, please double check the jars files were downloaded to the `libs` directory.

>**Note:**
>`gradlew clean` will delete the downloaded jars.

## Obfuscating

These instructions will guide you through the process of creating the project using DashO's New Project Wizard.

1. Start DashO and run the New Project Wizard.
    * Click `Next`.
    * Choose `Desktop App`.
    * Click `Next` and choose `SpringBean/MySpringApp.jar` as the application jar and enter the JDK location.
    * Click `Next` and add the jars in the `libs` directory as support jars.
    * Continue clicking `Next`, accepting the defaults, then click `Finish`.
2. Once the project is created, go to the Input->Options section.
    *   Check `Rename Reflected Classes`.
3. Go to the Output section.
    *   Choose `Create: Single Jar`.
    *   Enter the name `MyObfuscatedSpringApp.jar`.
4. Build the project. (Make sure you do not have any errors and that `MyObfuscatedSpringApp.jar` is created.)

## Running the application

These instructions will guide you through running the original and obfuscated versions of the application side by side.

1. Go back to your command prompt.
2. Start both applications (on a background thread).
    *   Windows
        *   Run the command: `start java -jar MySpringApp.jar` (You will see an app come up).
        *   Run the command: `start java -jar MyObfuscatedSpringApp.jar` (You will see another app come up).
    *   MacOS/Unix
        *   Run the command: `java -jar MySpringApp.jar &` (You will see an app come up).
        *   Run the command: `java -jar MyObfuscatedSpringApp.jar &` (You will see another app come up).
3. Click the `Life Cycle` button in both apps and read the output.
4. Click the `Properties` button in both apps and read the output.
5. Click the `Lookup/Replaced` button in both apps and read the output.
6. Click the `Constants` button in both apps and read the output.
7. Close the apps by clicking the `X`.
