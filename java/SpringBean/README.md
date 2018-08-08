# Spring Bean Handling

This sample illustrates how _PreEmptive Protection - DashO_ works with Spring Beans.
It will be compiled via [Ant](http://ant.apache.org), obfuscated with DashO and then both the original and obfuscated versions of the application should be run side-by-side.

## Setup

See the main [README](../README.md) for the requirements.

## Building

These instructions will guide you through the process of building the original app using Ant.

1.  Download `spring-framework-4.0.1.RELEASE-dist.zip` from  [repo.spring.io](http://repo.spring.io/release/org/springframework/spring/4.0.1.RELEASE/) or [maven.springframework.org](http://maven.springframework.org/release/org/springframework/spring/4.0.1.RELEASE/) (Please note other versions will probably work as well).
2.  Extract the following jars from that zip file and copy them to the `SpringBeans/libs` directory.
    *   `spring-beans-4.0.1.RELEASE.jar`
    *   `spring-context-4.0.1.RELEASE.jar`
    *   `spring-core-4.0.1.RELEASE.jar`
    *   `spring-expression-4.0.1.RELEASE.jar`
3.  Download `commons-logging-1.1.1.jar` from [http://commons.apache.org/logging/download_logging.cgi](http://commons.apache.org/logging/download_logging.cgi) and copy it to the `SpringBeans/libs` directory.
    This jar is needed by the Spring Framework jars listed above.
4.  Open a command prompt (or shell).
5.  Change to the `SpringBean` directory.
6.  Run the command: `ant compile` (Make sure you do not have any compile errors and that `MySpringApp.jar` is created).
7.  Run the command: `ant run` (Click on the buttons and verify no errors occur).

If any error occured during compiling or running, please double check the downloaded jar files.

## Obfuscating

These instructions will guide you through the process of creating the project using DashO's New Project Wizard.

1.  Start DashO and run the New Project Wizard.
    *   Click `Next`.
    *   Choose "An application packaged in a Jar file."
    *   Click `Next` and choose `SpringBean/MySpringApp.jar` as the application jar and enter the JDK location.
    *   Click `Next` and add the jars in the `libs` directory as support jars.
    *   Continue clicking `Next`, accepting the defaults, then click `Finish`.
2.  Once the project is created, go to the Input->Options section.
    *   Check `Rename Reflected Classes`.
3.  Go to the Output section.
    *   Choose `Create: Single Jar`.
    *   Enter the name `MyObfuscatedSpringApp.jar`.
4.  Build the project. (Make sure you do not have any errors and that `MyObfuscatedSpringApp.jar` is created.)

## Running the application

These instructions will guide you through running the original and obfuscated versions of the application side by side.

1.  Go back to your command prompt.
2.  Start both applications (on a background thread).
    *   Windows
        *   Run the command: `start java -jar MySpringApp.jar` (You will see an app come up).
        *   Run the command: `start java -jar MyObfuscatedSpringApp.jar` (You will see another app come up).
    *   Unix
        *   Run the command: `java -jar MySpringApp.jar &` (You will see an app come up).
        *   Run the command: `java -jar MyObfuscatedSpringApp.jar &` (You will see another app come up).
3.  Click the `Life Cycle` button in both apps and read the output.
4.  Click the `Properties` button in both apps and read the output.
5.  Click the `Lookup/Replaced` button in both apps and read the output.
6.  Click the `Constants` button in both apps and read the output.
7.  Close the apps by clicking the `X`.

Copyright 2018 [PreEmptive Solutions, LLC.](https://www.preemptive.com)
