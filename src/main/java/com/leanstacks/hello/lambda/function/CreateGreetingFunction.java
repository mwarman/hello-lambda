package com.leanstacks.hello.lambda.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.Context;
import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;
import com.leanstacks.hello.lambda.service.GreetingService;

/**
 * The CreateGreetingFunction class is a Function which creates personalized Greeting objects for a User. This class
 * implements the {@link Function} interface. The {@code execute} method serves as the entry point for function
 * execution.
 * 
 * @author Matt Warman
 *
 */
@Component
public class CreateGreetingFunction implements Function<User, Greeting> {

    /**
     * The Logger for this class.
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see com.leanstacks.hello.lambda.function.Function#execute(java.lang.Object,
     * com.amazonaws.services.lambda.runtime.Context)
     */
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
