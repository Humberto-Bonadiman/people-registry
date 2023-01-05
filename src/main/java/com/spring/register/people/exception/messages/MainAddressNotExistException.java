package com.spring.register.people.exception.messages;

public class MainAddressNotExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MainAddressNotExistException() {
        super("This person does not have a main address");
    }
}
