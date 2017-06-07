package com.leanstacks.hello.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;

public class GreetingRequestHandler implements RequestHandler<User, Greeting> {

    @Override
    public Greeting handleRequest(User user, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("> handleRequest\n");
        logger.log("  user.name: " + user.getName() + "\n");

        String greetingText = String.format("Hello %1s!", user.getName());

        Greeting greeting = new Greeting(greetingText);
        logger.log("  greeting.text: " + greeting.getText() + "\n");

        logger.log("< handleRequest\n");
        return greeting;
    }

}
