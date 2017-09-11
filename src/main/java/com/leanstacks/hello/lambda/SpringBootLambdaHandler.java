package com.leanstacks.hello.lambda;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.leanstacks.hello.lambda.model.Greeting;

@SpringBootApplication
public class SpringBootLambdaHandler implements RequestHandler<Map<String, Object>, Object> {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootLambdaHandler.class);

    private static ApplicationContext applicationContext;

    protected static ApplicationContext getApplicationContext(final String... args) {
        logger.info("> getApplicationContext");
        logger.debug("my debug message");
        if (applicationContext == null) {
            logger.info("- start building ApplicationContext");
            applicationContext = SpringApplication.run(SpringBootLambdaHandler.class, args);
            logger.info("-   end building ApplicationContext");
        }
        logger.info("< getApplicationContext");
        return applicationContext;
    }

    @Override
    public Object handleRequest(Map<String, Object> input, Context context) {
        logger.info("> handleRequest");
        logger.info("- input:{}", input);

        try {
            ApplicationContext applicationContext = getApplicationContext();
        } catch (Exception ex) {
            logger.error("Exception building ApplicationContext", ex);
        }

        Greeting greeting = new Greeting();
        greeting.setText("Hello World!");

        logger.info("< handleRequest");
        return greeting;
    }

    /**
     * 
     * Entry point for the application.
     * 
     * @param args Command line arguments.
     */
    public static void main(final String... args) throws Exception {
        logger.info("> main");
        getApplicationContext(args);
        logger.info("< main");
    }

}
