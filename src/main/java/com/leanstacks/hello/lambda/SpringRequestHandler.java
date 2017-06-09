package com.leanstacks.hello.lambda;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class SpringRequestHandler {

    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(
        ApplicationConfiguration.class.getPackage().getName());

    protected static ApplicationContext getApplicationContext() {
        return SpringRequestHandler.APPLICATION_CONTEXT;
    }

}
