package com.leanstacks.hello.lambda.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    public static final String NAME = "name";

    @Test
    public void testUser() {
        final User entity = new User();
        entity.setName(NAME);
        Assert.assertEquals(NAME, entity.getName());
    }

    public static User getMock() {
        final User entity = new User();
        entity.setName(NAME);
        return entity;
    }

}
