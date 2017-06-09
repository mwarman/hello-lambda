package com.leanstacks.hello.lambda.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.leanstacks.hello.lambda.function.CreateGreetingFunction;
import com.leanstacks.hello.lambda.function.Function;
import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;

/**
 * <p>
 * The {@code GreetingRequestHandler} class encapsulates AWS Lambda function requests for the Greeting entity. The
 * request handler methods leverage the Spring Framework to fulfill their behavior; therefore, the class extends
 * {@link SpringRequestHandler}.
 * </p>
 * <p>
 * Individual public methods on this class are invoked directly by the AWS Lambda service. Lambda functions authored in
 * Java are configured with request signatures in the format:<br/>
 * {@code fully.qualified.className::methodName} <br/>
 * </p>
 * <p>
 * For example, the {@code createGreeting} method is declared as an AWS Lambda function request handler with a signature
 * {@code com.leanstacks.hello.lambda.handler.GreetingRequestHandler::createGreeting}.
 * </p>
 * 
 * @author Matt Warman
 *
 */
public class GreetingRequestHandler extends SpringRequestHandler {

    /**
     * The Logger for this class. Used to write to CloudWatch Logs.
     */
    private static final Logger logger = LoggerFactory.getLogger(GreetingRequestHandler.class);

    /**
     * The AWS Lambda function request handler method for creating Greeting entities. The function handler signature is:
     * {@code com.leanstacks.hello.lambda.handler.GreetingRequestHandler::createGreeting}.
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

}
