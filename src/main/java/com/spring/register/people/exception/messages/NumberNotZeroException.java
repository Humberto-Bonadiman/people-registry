package com.spring.register.people.exception.messages;

public class NumberNotZeroException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NumberNotZeroException() {
        super("Number cannot be 0");
    }
}
