package com.leanstacks.hello.lambda.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.leanstacks.hello.lambda.ApplicationConfiguration;

public abstract class SpringRequestHandler {

    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(
        ApplicationConfiguration.class.getPackage().getName());

    protected static ApplicationContext getApplicationContext() {
        return SpringRequestHandler.APPLICATION_CONTEXT;
    }

}
