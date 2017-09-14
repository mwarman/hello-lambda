package com.leanstacks.hello.lambda.function;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leanstacks.hello.lambda.model.FunctionInput;
import com.leanstacks.hello.lambda.model.FunctionOutput;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LowercaseFunctionTest {

    @Test
    public void testApply() {
        final FunctionInput input = new FunctionInput();
        input.setInput("Text");

        final LowercaseFunction function = new LowercaseFunction();
        final FunctionOutput output = function.apply(input);

        Assert.assertEquals("text", output.getOutput());
    }

}
