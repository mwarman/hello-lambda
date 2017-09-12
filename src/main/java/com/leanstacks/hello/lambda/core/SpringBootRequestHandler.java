package com.leanstacks.hello.lambda.core;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.leanstacks.hello.lambda.Application;

public class SpringBootRequestHandler<I, O> implements RequestHandler<I, Object> {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootRequestHandler.class);

    private static final String CONFIG_FUNCTION_NAME = "function.name";
    private static final String CONFIG_FUNCTION_NAME_DEFAULT = "function";

    private AtomicBoolean initialized = new AtomicBoolean();

    private ConfigurableApplicationContext applicationContext;

    private Function<I, O> function;

    @SuppressWarnings("unchecked")
    protected void initialize() {

        if (!this.initialized.compareAndSet(false, true)) {
            // if previously initialized
            return;
        }

        logger.info("Initializing...");
        SpringApplicationBuilder appBuilder = new SpringApplicationBuilder(Application.class);
        ConfigurableApplicationContext appContext = appBuilder.web(false).run();
        appContext.getAutowireCapableBeanFactory().autowireBean(this);

        logger.info("Identifying function to run...");
        String functionName = appContext.getEnvironment().getProperty(CONFIG_FUNCTION_NAME);
        if (functionName == null) {
            functionName = appContext.getEnvironment().getProperty(CONFIG_FUNCTION_NAME_DEFAULT);
        }
        this.function = appContext.getBean(functionName, Function.class);

        this.applicationContext = appContext;

    }

    @Override
    public Object handleRequest(I input, Context context) {
        logger.info("> handleRequest");

        initialize();
        if (this.function == null) {
            throw new IllegalStateException("No function defined");
        }

        Object output = this.function.apply(input);

        logger.info("< handleRequest");
        return output;
    }

}
