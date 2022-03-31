# Spring Boot starter data jpa sample project
This sample illustrates how _PreEmptive Protection™ DashO™_ works with Spring Boot.
It will be compiled via [Maven](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/htmlsingle/), 
obfuscated with DashO, and then both the original and obfuscated versions of the application should be run side-by-side.

## Reference

* [Maven Reference](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/htmlsingle/)
* [More Information](./HELP.md)

## Debug

* Run `./mvnw install`
* Run `./mvnw spring-boot:run`
* Open http://localhost:8080

### Debug H2 Database

* Open http://localhost:8080/h2-console

## Build

* Run `./mvnw install`
* Output: `./target/spring-boot-starter-data-jpa-0.0.1-SNAPSHOT.war`

## Run War

* Run `java -jar ./target/spring-boot-starter-data-jpa-0.0.1-SNAPSHOT.war`

## Obfuscating
These istructions will guide you through the process of creating the project using DashO's New Project Wizard.

1. Start DashO and run the New Project Wizard.
    * Click `Next`.
    * Choose `Web`.
    * Click `Next` and choose `./target/spring-boot-starter-data-jpa-0.0.1-SNAPSHOT.war`.
    * Click `Next` and skip choosing the annotation entry points.
    * Clickclick `Finish`.
2. Go to the Output section.
    *   Choose Destination `WAR`.
3. Build the project. (Make sure you do not have any errors and that `./target/dasho/spring-boot-starter-data-jpa-0.0.1-SNAPSHOT.war` is created.)
4. Run obfuscated war `java -jar ./target/dasho/spring-boot-starter-data-jpa-0.0.1-SNAPSHOT.war`.
    * Then open http://locahost:8080
## Application
1. http://localhost:8080/persons
	* method: get -> List all persons
	* method: post-> save person detail
2. http://localhost:8080/persons/{id}
	* method: get -> display person details given id
	* method: put -> update person details if given id exist
	* method: delete -> delete person details of given id
	
## Automated Tests
* Run `./mvnw test -Dtest=com.dasho.springbootstarterdatajpa.SpringBootStarterDataJpaApplicationTests`
