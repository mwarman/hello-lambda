# AWS Lambda - Hello World Function

## Acknowledgements

This is a [LEAN**STACKS**](http://www.leanstacks.com) solution.

## Getting Started

This is an [AWS Lambda](https://aws.amazon.com/lambda) function authored in Java which uses the Spring Framework for dependency injection and enterprise features.

## Features

### Request Handlers
Request handler methods are the entry point into your custom Lambda function. The AWS Lambda service invokes a request handler method, passing as method parameters the Input and Context objects. The request handler method returns the Output object to the AWS Lambda service. From a logical application design perspective, think of Request Handler classes as analogous to traditional Spring Controller (or RestController) classes. Each Request Handler class may implement one to many handler methods which are invoked by distinct AWS Lambda configured functions much like a Spring Controller method is invoked based upon its `@RequestMapping` value. If a Request Handler class implements more than one handler method, group the methods into classes containing logically similar activities as you would a Controller class.

The role of Request Handler classes and methods is simply to serve as the entry point into your custom Lambda function. Their job is to receive the Input and Context, retrieve a `Function` Bean from the Spring ApplicationContext, execute the Function, and return the Output of the Function to the AWS Lambda service. The business logic should be implemented within the Function or Service application layers executed within the Spring ApplicationContext.

```
    /**
     * The AWS Lambda function request handler method for creating Greeting entities.
     * The function handler signature is:
     *
     *   com.leanstacks.hello.lambda.handler.GreetingRequestHandler::createGreeting
     *
     * @param user A User object. The Lambda function input.
     * @param context A Lambda Context object.
     * @return A Greeting object. The Lambda function output.
     */
    public Greeting createGreeting(User user, Context context) {
        logger.info("> createGreeting");

        Function<User, Greeting> function = getApplicationContext().getBean(CreateGreetingFunction.class);
        Greeting greeting = function.execute(user, context);

        logger.info("< createGreeting");
        return greeting;
    }

```

Each Request Handler class extends `SpringRequestHandler`. The `SpringRequestHandler` class has a static member attribute containing the initialized ApplicationContext.  Using a static member attribute in the parent class, SpringRequestHandler, ensures that the ApplicationContext is initialized once when the AWS Lambda service deploys the custom function to the AWS Lambda Service. By utilizing this approach, the ApplicationContext is initialized prior to the execution of your function which dramatically improves performance and reduces cost.

It is important that all of your custom Lambda function logic be implemented in a **stateless** manner. AWS Lambda functions should not maintain state. AWS makes no guarantee that subsequent client requests will be routed to the same Lambda function instance. If an application requires state management, explore the use of a stateful application hosted on multiple EC2 instances in different availability zones coupled with an elastic load balancer with session affinity (sticky sessions) enabled.

### Functions
Function classes orchestrate the behavior required to complete the AWS Lambda function. Function classes are managed Spring Beans and, therefore, are receive all the benefits of the Spring Framework such as dependency injection.

The `Function` interface defines the `execute` method which is invoked by the Request Handler. The execute method accepts the Input and Lambda Context objects and returns an Output object to the Request Handler.

```
@Component
public class CreateGreetingFunction implements Function<User, Greeting> {

    private static final Logger logger = LoggerFactory.getLogger(CreateGreetingFunction.class);

    /**
     * The GreetingService business service.
     */
    private final GreetingService greetingService;

    /**
     * Constructs a CreateGreetingFunction object with dependencies.
     *
     * @param greetingService A GreetingService.
     */
    @Autowired
    public CreateGreetingFunction(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public Greeting execute(User user, Context context) {
        logger.info("> execute");
        logger.debug("  user.name: {}", user.getName());

        Greeting greeting = greetingService.create(user);
        logger.debug("  greeting.text: {}", greeting.getText());

        logger.info("< execute");
        return greeting;
    }

}

```

### Spring Components
You may include any annotation configured Spring components in the project including: `@Configuration`, `@Component`, `@Service`, `@Repository`, etc.

An `AnnotationConfigApplicationContext` is initialized when the function is deployed to the AWS Lambda service. The ApplicationContext performs a component scan beginning in the project base package and includes all sub-packages.  All Spring stereotype annotated classes are automatically loaded into the ApplicationContext. The project base package is derived from the location of the `ApplicationConfiguration` class.

The `ApplicationConfiguration` class is the main `@Configuration` class of the project and should be located in the base package of the application.  For example, the base package may be `com.example.hello.lambda` with sub-packages such as: `.function`, `.handler`, `.model`, `.repository`, and `.service`. By creating the `ApplicationConfiguration` class in the `com.example.hello.lambda` package, the component scanner will automatically include all Spring components in the base package as well as the sub-packages without additional, explicit configuration.

### Other Spring Projects
By including the Spring Framework as a foundation application layer, your Lambda functions may include any other official or community Spring Projects which integrate with the Spring Framework such as Spring Data, Spring Security, etc.

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
