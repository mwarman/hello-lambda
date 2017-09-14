package com.leanstacks.hello.lambda.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionInputTest {

    public static final String INPUT = "input";

    @Test
    public void testFunctionInput() {
        final FunctionInput entity = new FunctionInput();
        entity.setInput(INPUT);

        Assert.assertEquals(INPUT, entity.getInput());
    }

    public static FunctionInput getMock() {
        final FunctionInput entity = new FunctionInput();
        entity.setInput(INPUT);
        return entity;
    }

}
