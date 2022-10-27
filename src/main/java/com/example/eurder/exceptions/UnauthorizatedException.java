package com.example.eurder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizatedException extends RuntimeException {
    public UnauthorizatedException() {
        super("You don't have the authorization of this feature");
    }
}
