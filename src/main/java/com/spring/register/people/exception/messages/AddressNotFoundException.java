package com.spring.register.people.exception.messages;

public class AddressNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AddressNotFoundException() {
        super("Address not found!");
    }
}
