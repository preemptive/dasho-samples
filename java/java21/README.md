# Java 21 Samples

## Basic Requirements
* Java 21


## Environment Setup
* Set env variable `JDK_JAVA_OPTIONS=-Djdk.attach.allowAttachSelf=true` (required for JEP451)

## Run
* Build the jar `mvn clean install`
* Run the jar `java --enable-preview --add-modules jdk.incubator.vector -jar target/dasho-maven-3-1.0-SNAPSHOT.jar`
* To run one JEP use command `java --enable-preview --add-modules jdk.incubator.vector -jar target/dasho-maven-3-1.0-SNAPSHOT.jar JEP451`
