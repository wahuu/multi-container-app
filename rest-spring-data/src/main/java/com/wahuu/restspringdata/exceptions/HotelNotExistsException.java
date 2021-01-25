package com.wahuu.restspringdata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelNotExistsException extends RuntimeException {

    public HotelNotExistsException(String message) {
        super(message);
    }
}
