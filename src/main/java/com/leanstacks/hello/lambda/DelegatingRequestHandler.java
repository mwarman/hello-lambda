package com.leanstacks.hello.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.leanstacks.hello.lambda.handler.CreateGreetingRequestHandler;
import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;

public class DelegatingRequestHandler extends SpringRequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(DelegatingRequestHandler.class);

    public Greeting createGreeting(User user, Context context) {
        logger.info("> createGreeting");

        RequestHandler<User, Greeting> handler = getApplicationContext().getBean(CreateGreetingRequestHandler.class);
        Greeting greeting = handler.handleRequest(user, context);

        logger.info("< createGreeting");
        return greeting;
    }

}
