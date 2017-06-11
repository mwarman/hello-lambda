package com.leanstacks.hello.lambda.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.leanstacks.hello.lambda.ApplicationConfiguration;

/**
 * <p>
 * SpringRequestHandler is an abstract superclass for all AWS Lambda RequestHandler implementations which wish to
 * leverage the Spring Framework to fulfill the request.
 * </p>
 * <p>
 * The SpringRequestHandler creates an ApplicationContext, specifically an {@code AnnotationConfigApplicationContext}.
 * The ApplicationContext is stored as a static attribute in the RequestHandler which has the following characteristics:
 * <ul>
 * <li>started when the Lambda function is uploaded rather than upon the first request; improves performance
 * <li>reused by the Lambda function so long as AWS keeps the function execution environment cached
 * </ul>
 * Because the Spring components are reused and as a general best practice for all AWS Lambda functions, all components
 * which comprise the Lambda function must be designed to be stateless. If any classes persist state between execution,
 * it may negatively impact the behavior of subsequent function invocations.
 * </p>
 * 
 * @author Matt Warman
 *
 */
public abstract class SpringRequestHandler {

    /**
     * The Spring ApplicationContext.
     */
    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(
        ApplicationConfiguration.class.getPackage().getName());

    /**
     * Returns the Spring ApplicationContext.
     * 
     * @return An ApplicationContext object.
     */
    protected static ApplicationContext getApplicationContext() {
        return SpringRequestHandler.APPLICATION_CONTEXT;
    }

}
