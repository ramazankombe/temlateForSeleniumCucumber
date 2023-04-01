Java TestNG Cucumber BDD Framework
This is a sample Java TestNG Cucumber BDD framework, designed to automate test cases using behavior-driven development (BDD) methodology.

Getting Started
Prerequisites
JDK 17
Maven 3.5 or higher
IDE (Eclipse or IntelliJ recommended)
Installing
Add project from zip file to your local machine.
Import the project into your IDE.
Update the pom.xml file with the required dependencies and plugins.
Update the src/test/resources/features directory with the relevant feature files.
Update the src/test/java/stepDefinitions directory with the relevant step definitions.
Update the src/test/java/runners directory with the relevant test runners.
Build the project using Maven.
Running the Tests
To run the tests, simply execute the relevant test runner file(s) located in the src/test/java/runners directory.

Alternatively, you can use the following command to run the tests from the command line:

bash mvn clean test

Built With
Java
TestNG
Cucumber
Maven

Authors
Ismail Kömbe



Acknowledgments
I put exemplarily cloud provide connection inside Driver class. 
It is possible to run test cases in parallel
This humble framework can be dockerize and run with correct docker-entrypoint.sh file (Not Created)