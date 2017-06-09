package com.leanstacks.hello.lambda.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.Context;
import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;
import com.leanstacks.hello.lambda.service.GreetingService;

@Component
public class CreateGreetingFunction implements Function<User, Greeting> {

    private static final Logger logger = LoggerFactory.getLogger(CreateGreetingFunction.class);

    private final GreetingService greetingService;

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
