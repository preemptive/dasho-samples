# Java Multi Module Project (JPMS)

This project provides an example of using Dasho to obfuscate multi java modules (JPMS) packages. 

## Requirements

* Maven


## Build
First build the project.

* Run `mvn install`


## Open DashO GUI
Open up the PreEmptive DashO GUI application.

* Open up the DashO GUI

* Compile the App Module
* Open up `./app/app-module.dox`
* Go to the "Support" menu, then set the JDK to Java 11 JDK
* Run the DashO compile

* Compile the Core Module
* Open up `./core/core-module.dox`
* Go to the "Support" menu, then set the JDK to Java 11 JDK
* Run the Dasho compile


## Test
Test the builds.

* On Windows replace `:` with `;` in the classpath.

### Test Jars

* Run `java -cp "./app/target/app-1.0.0.jar:./core/target/core-1.0.0.jar" com.example.sample.app.App`

### Test Obfuscated Jars

* Run `java -cp "./app/target/obfuscated/app-1.0.0.jar:./core/target/obfuscated/core-1.0.0.jar" com.example.sample.app.App`


