package com.leanstacks.hello.lambda.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingServiceTest {

    private MockEnvironment env;

    private GreetingService greetingService;

    @Before
    public void setUp() {
        env = new MockEnvironment();
        env.setProperty("GREETING_PREFIX", "Mock");
        greetingService = new GreetingServiceBean(env);
    }

    @Test
    public void testCreate() {
        final String expectedGreetingText = "Mock Name!";
        final User user = new User();
        user.setName("Name");

        final Greeting greeting = greetingService.create(user);

        Assert.assertEquals(expectedGreetingText, greeting.getText());
    }

    @Test
    public void testCreateWithEnvironmentDefaults() {
        env = new MockEnvironment();
        greetingService = new GreetingServiceBean(env);

        final String expectedGreetingText = "Hello Name!";
        final User user = new User();
        user.setName("Name");

        final Greeting greeting = greetingService.create(user);

        Assert.assertEquals(expectedGreetingText, greeting.getText());
    }

}
