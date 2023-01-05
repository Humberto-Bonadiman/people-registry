package com.spring.register.people.exception.messages;

public class NumberLessThanZeroException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NumberLessThanZeroException() {
        super("Number cannot be less than 1");
    }
}
