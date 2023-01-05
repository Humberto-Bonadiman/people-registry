package com.spring.register.people.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.spring.register.people.exception.messages.AddressNotFoundException;
import com.spring.register.people.exception.messages.MainAddressNotExistException;
import com.spring.register.people.exception.messages.NumberLessThanZeroException;
import com.spring.register.people.exception.messages.PersonNotFoundException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler({
            InvalidFormatException.class,
            PropertyValueException.class,
            ValueInstantiationException.class,
            NumberLessThanZeroException.class,
            MainAddressNotExistException.class
    })
    public ResponseEntity<Object> handlerBadRequest(Exception exception) {
        return new ResponseEntity<>(new DataError(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            PersonNotFoundException.class,
            AddressNotFoundException.class
    })
    public ResponseEntity<Object> handlerNotFound(Exception exception) {
        return new ResponseEntity<>(new DataError(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
