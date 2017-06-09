# AWS Lambda - Hello World Function

## Acknowledgements

This is a [LEAN**STACKS**](http://www.leanstacks.com) solution.

## Getting Started

This is an [AWS Lambda](https://aws.amazon.com/lambda) function authored in Java which uses the Spring Framework for dependency injection and enterprise features.

## Features

### Request Handlers
Discuss the link from AWS Lambda function request handlers to this project. Discuss the organization of handler classes and methods; grouping methods into a class similar to how you would a Controller component.

Discuss the importance of implementing the SpringRequestHandler abstract class and the implication of declaring the ApplicationContext in the RequestHandler class so that it is initialized when the function is deployed to AWS versus at runtime.

### Functions
Discuss the Function interface and implementation classes.  One per AWS Lambda function. This is the main entry point in the Spring application for each function.

### Spring Components
Discuss the use of Configuration, Service, and other classes.

### Other Spring Projects
The project illustrates how to use the HSQLDB in-memory database which is useful for rapid prototyping or unit test execution in a continuous integration environment.

## Languages

This project is authored in Java.

## Installation

### Fork the Repository

Fork the [hello-lambda](https://github.com/mwarman/hello-lambda) project on GitHub.  Clone the project to the host machine.

### Dependencies

The project requires the following dependencies be installed on the host machine:

* Java Development Kit 8 or later
* Gradle 3.5 \*

\* The Gradle Wrapper is bundled with this project. Gradle tasks may be used without installing Gradle CLI by substituting `./gradlew` for `gradle` in the instructions below.

### Spring Tool Suite or Eclipse

This project uses Checkstyle static code analysis and reporting to ensure contributions are formatted in a consistent manner.  To ease the burden for contributing software engineers, the Eclipse Java Code Formatter configuration is supplied.  The formatting configuration may be used in Eclipse, the Spring Tool Suite, or any derivative of the Eclipse IDE.

The Java Code Formatter configuration is located in the source at: `etc/eclipse/java-formatter.xml`.

After importing the project into Eclipse, edit the project properties by selecting *Properties* from the *Project* drop-down menu.  Then, expand the *Java Code Style* menu and select *Formatter*.  Click the *Import* button to import the configuration file.  Next, expand the *Java Editor* menu and select *Save Actions*.  Ensure that the following selections are checked:
* Enable project specific settings
* Perform the selected actions on save
  * Format source code
    * Format all lines
  * Organize imports

## Running

The project uses [Gradle](http://gradle.org/) for build, package, and test workflow automation.  

### Gradle

The following Gradle tasks are the most commonly used.

#### build (default)

The `build` Gradle task is prepares the application for distribution. The application and all dependencies are packaged into a single, JAR file.

This task is ideal for use on continuous integration servers such as Jenkins, etc. because it produces unit test, code coverage, and static analysis reports.

To execute the `build` Gradle task, type the following command at a terminal prompt in the project base directory.

```
./gradlew clean build
```

The `clean` and `build` tasks are the default tasks for this project.  Therefore, simply typing `gradle` (or `./gradlew` to use the Gradle Wrapper) will produce the same result as `gradle clean build`.

```
./gradlew
```

The application distribution artifact is placed in the /build/libs directory and is named using the project name from the `build.gradle` file.  The task produces two JAR files. One contains only the project class files and the second, suffixed with `-all.jar` is a *shaded* JAR file, meaning that it contains all required runtime dependencies in addition to the project class files. When deploying to AWS Lambda, use the *shaded* JAR file.
