package com.leanstacks.hello.lambda.function;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.leanstacks.hello.lambda.model.FunctionInput;
import com.leanstacks.hello.lambda.model.FunctionOutput;

@Component
public class UppercaseFunction implements Function<FunctionInput, FunctionOutput> {

    private static final Logger logger = LoggerFactory.getLogger(UppercaseFunction.class);

    @Override
    public FunctionOutput apply(FunctionInput input) {
        logger.info("> apply");

        FunctionOutput output = new FunctionOutput();
        output.setOutput(input.getInput().toUpperCase());

        logger.info("< apply");
        return output;
    }

}
