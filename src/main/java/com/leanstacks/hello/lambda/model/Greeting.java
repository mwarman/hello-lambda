package com.leanstacks.hello.lambda.model;

public class Greeting {

    private String text;

    public Greeting() {

    }

    public Greeting(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

}
