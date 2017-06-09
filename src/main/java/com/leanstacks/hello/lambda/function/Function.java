package com.leanstacks.hello.lambda.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.leanstacks.hello.lambda.handler.SpringRequestHandler;

/**
 * <p>
 * The Function interface describes a single Lambda function. A Function is not directly invoked by the AWS Lambda
 * service, but rather by a {@link SpringRequestHandler}. See the SpringRequestHandler class for more detail.
 * </p>
 * <p>
 * The {@code execute} method serves as the entry point for the Function. Note that it has the same parameters as the
 * AWS Lambda RequestHandler interface; this is by design. It allows the Lambda service to perform pre- and
 * post-processing on the Input and Output objects.
 * </p>
 * 
 * @author Matt Warman
 *
 * @param <I> The input type.
 * @param <O> The output type.
 */
public interface Function<I, O> {

    /**
     * Execute the Function behavior with the supplied Input and Lambda Context.
     * 
     * @param input The function Input.
     * @param context The AWS Lambda Context object.
     * @return The function Output.
     */
    O execute(final I input, final Context context);

}
