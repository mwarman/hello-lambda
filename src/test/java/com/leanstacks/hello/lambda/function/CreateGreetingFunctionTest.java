package com.leanstacks.hello.lambda.function;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;
import com.leanstacks.hello.lambda.service.GreetingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateGreetingFunctionTest {

    @MockBean
    private transient GreetingService greetingService;

    @Test
    public void testApply() {
        final User user = new User();
        user.setName("Joe");
        final String expectedText = "Hello Joe!";
        final Greeting mockGreeting = new Greeting();
        mockGreeting.setText(expectedText);

        when(greetingService.create(any(User.class))).thenReturn(mockGreeting);

        final CreateGreetingFunction function = new CreateGreetingFunction(greetingService);
        final Greeting greeting = function.apply(user);

        verify(greetingService, times(1)).create(any(User.class));

        Assert.assertEquals(expectedText, greeting.getText());
    }

}
