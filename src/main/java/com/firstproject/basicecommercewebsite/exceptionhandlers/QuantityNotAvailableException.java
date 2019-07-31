package com.firstproject.basicecommercewebsite.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuantityNotAvailableException extends RuntimeException {

    public QuantityNotAvailableException(String message) {
        super(message);
    }

}
