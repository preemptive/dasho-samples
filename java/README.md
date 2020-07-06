# Java Samples

## Basic Requirements

* Java 8 (or later)
* [Ant](http://ant.apache.org) v1.9 (or later)

## Environment Setup

Before running these samples, they need to know where PreEmptive Protection™ DashO™ and the JDK are located.

Set an environment variable named `DASHO_HOME` to the [location](https://www.preemptive.com/dasho/pro/userguide/en/getting_started_first.html#install_dir) of DashO.
It can also be passed via the Ant command line: `-Ddasho.home={DashO's Installation Directory}`.

Set an environment variable named `JDK_HOME` to the location of the JDK.  This will probably be the same value you have set for `JAVA_HOME`.

## Samples

* [multidir](multidir) - Cross-directory obfuscation using the `merge="false"` output option.
* [multijar](multijar) - Cross-jar obfuscation using the `merge="false"` output option.
* [simpleapp](simpleapp) - A hello world type application.
* [SpringBean](SpringBean) - An Spring Application showing how DashO deals with Spring Beans.
