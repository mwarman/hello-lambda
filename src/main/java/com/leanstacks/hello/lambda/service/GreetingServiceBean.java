package com.leanstacks.hello.lambda.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;

/**
 * The GreetingServiceBean implements the behaviors of the {@link GreetingService}.
 * 
 * @author Matt Warman
 *
 */
@Service
public class GreetingServiceBean implements GreetingService {

    /**
     * The Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceBean.class);

    /**
     * A String template for formatting Greeting text.
     */
    private static final String GREETING_FORMAT = "%1s %2s!";

    /**
     * The Spring Environment.
     */
    private Environment env;

    /**
     * Construct a GreetingServiceBean with the supplied parameters.
     * 
     * @param env An Environment object. Spring's Environment wrapper.
     */
    @Autowired
    public GreetingServiceBean(Environment env) {
        this.env = env;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.leanstacks.hello.lambda.service.GreetingService#create(com.leanstacks.hello.lambda.model.User)
     */
    @Override
    public Greeting create(final User user) {
        logger.info("> create");

        final String greetingText = String.format(GREETING_FORMAT, env.getProperty("GREETING_PREFIX", "Hello"),
            user.getName());

        final Greeting greeting = new Greeting(greetingText);

        logger.info("< create");
        return greeting;
    }

}
