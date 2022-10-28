package com.example.eurder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundexception extends RuntimeException {
    public NotFoundexception() {
        super("Not Found! Try Again!");
    }
}
