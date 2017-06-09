package com.leanstacks.hello.lambda;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;

public class DelegatingRequestHandler extends AbstractSpringRequestHandler<User, Greeting> {

    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(
        DelegatingRequestHandler.class.getPackage().getName());

    @Override
    public ApplicationContext getApplicationContext() {
        return DelegatingRequestHandler.APPLICATION_CONTEXT;
    }

}
