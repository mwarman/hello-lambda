package com.leanstacks.hello.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;
import com.leanstacks.hello.lambda.service.GreetingService;

@Component
public class GreetingRequestHandler implements RequestHandler<User, Greeting> {

    private static final Logger logger = LoggerFactory.getLogger(GreetingRequestHandler.class);

    private final GreetingService greetingService;

    @Autowired
    public GreetingRequestHandler(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public Greeting handleRequest(User user, Context context) {
        logger.info("> handleRequest");
        logger.debug("  user.name: {}", user.getName());

        Greeting greeting = greetingService.create(user);
        logger.debug("  greeting.text: {}", greeting.getText());

        logger.info("< handleRequest");
        return greeting;
    }

}
