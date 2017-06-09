package com.leanstacks.hello.lambda.service;

import com.leanstacks.hello.lambda.model.Greeting;
import com.leanstacks.hello.lambda.model.User;

public interface GreetingService {

    Greeting create(User user);

}
