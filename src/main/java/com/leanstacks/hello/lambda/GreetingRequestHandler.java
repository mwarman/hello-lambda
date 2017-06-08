package com.leanstacks.hello.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;

public class GreetingRequestHandler implements RequestHandler<User, Greeting> {

    private static final Logger logger = LoggerFactory.getLogger(GreetingRequestHandler.class);

    @Override
    public Greeting handleRequest(User user, Context context) {
        logger.info("> handleRequest");
        logger.debug("  user.name: {}", user.getName());

        String greetingPrefix = System.getenv("GREETING_PREFIX");

        String greetingText = String.format("%1s %2s!", greetingPrefix, user.getName());

        Greeting greeting = new Greeting(greetingText);
        logger.debug("  greeting.text: {}", greeting.getText());

        logger.info("< handleRequest");
        return greeting;
    }

}
