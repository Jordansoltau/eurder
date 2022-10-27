package com.example.eurder.exceptions;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("Wrong Password");
    }
}
