package com.leanstacks.hello.lambda.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingTest {

    public static final String TEXT = "text";

    @Test
    public void testGreeting() {
        final Greeting entity = new Greeting();
        entity.setText(TEXT);

        Assert.assertEquals(TEXT, entity.getText());
    }

    public static Greeting getMock() {
        final Greeting entity = new Greeting();
        entity.setText(TEXT);
        return entity;
    }

}
