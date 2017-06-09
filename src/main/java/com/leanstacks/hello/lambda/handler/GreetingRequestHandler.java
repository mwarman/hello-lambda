package com.leanstacks.hello.lambda.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.leanstacks.hello.lambda.function.CreateGreetingFunction;
import com.leanstacks.hello.lambda.function.Function;
import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;

public class GreetingRequestHandler extends SpringRequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(GreetingRequestHandler.class);

    public Greeting createGreeting(User user, Context context) {
        logger.info("> createGreeting");

        Function<User, Greeting> function = getApplicationContext().getBean(CreateGreetingFunction.class);
        Greeting greeting = function.execute(user, context);

        logger.info("< createGreeting");
        return greeting;
    }

}
