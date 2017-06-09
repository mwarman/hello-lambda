package com.leanstacks.hello.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public abstract class AbstractSpringRequestHandler<I, O> implements RequestHandler<I, O>, ApplicationContextProvider {

    private final RequestHandler handler;

    public AbstractSpringRequestHandler() {
        handler = getApplicationContext().getBean(RequestHandler.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public O handleRequest(final I input, final Context context) {
        return (O) handler.handleRequest(input, context);
    }

}
