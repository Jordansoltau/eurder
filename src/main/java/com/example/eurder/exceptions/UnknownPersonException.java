package com.example.eurder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnknownPersonException extends RuntimeException {
    public UnknownPersonException() {
        super("This id is unknown");
    }
}
