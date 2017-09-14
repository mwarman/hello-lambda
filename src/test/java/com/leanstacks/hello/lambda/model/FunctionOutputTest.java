package com.leanstacks.hello.lambda.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionOutputTest {

    public static final String OUTPUT = "output";

    @Test
    public void testFunctionOutput() {
        final FunctionOutput entity = new FunctionOutput();
        entity.setOutput(OUTPUT);

        Assert.assertEquals(OUTPUT, entity.getOutput());
    }

    public static FunctionOutput getMock() {
        final FunctionOutput entity = new FunctionOutput();
        entity.setOutput(OUTPUT);
        return entity;
    }

}
