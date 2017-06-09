package com.leanstacks.hello.lambda.service;

import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;

/**
 * The business service interface for the {@code GreetingService} exposes the public behaviors for operating on the
 * Greeting entity. Clients should use the GreetingService interface rather than implementation classes.
 * 
 * @author Matt Warman
 *
 */
public interface GreetingService {

    /**
     * Create a Greeting using the supplied parameters.
     * 
     * @param user A User object.
     * @return A Greeting.
     */
    Greeting create(User user);

}
