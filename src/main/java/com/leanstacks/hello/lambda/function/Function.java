package com.leanstacks.hello.lambda.function;

import com.amazonaws.services.lambda.runtime.Context;

public interface Function<I, O> {

    O execute(final I input, final Context context);

}
