package com.leanstacks.hello.lambda.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;

@Service
public class GreetingServiceBean implements GreetingService {

    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceBean.class);

    private static final String GREETING_FORMAT = "%1s %2s!";

    @Override
    public Greeting create(final User user) {
        logger.info("> create");

        final String greetingText = String.format(GREETING_FORMAT, "Hello", user.getName());

        final Greeting greeting = new Greeting(greetingText);

        logger.info("< create");
        return greeting;
    }

}
