package com.leanstacks.hello.lambda.function;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;
import com.leanstacks.hello.lambda.service.GreetingService;

@Component
public class CreateGreetingFunction implements Function<User, Greeting> {

    private static final Logger logger = LoggerFactory.getLogger(CreateGreetingFunction.class);

    private GreetingService greetingService;

    public CreateGreetingFunction(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public Greeting apply(User user) {
        logger.info("> apply");

        Greeting greeting = greetingService.create(user);

        logger.info("< apply");
        return greeting;
    }

}
