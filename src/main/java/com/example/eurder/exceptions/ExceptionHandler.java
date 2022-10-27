package com.example.eurder.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    protected void userID(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundexception.class)
    protected void userID(NotFoundexception ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(NOT_FOUND.value(), ex.getMessage());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizatedException.class)
    protected void userID(UnauthorizatedException ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(NOT_FOUND.value(), ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnknownPersonException.class)
    protected void userID(UnknownPersonException ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(NOT_FOUND.value(), ex.getMessage());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(WrongPasswordException.class)
    protected void userID(WrongPasswordException ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(NOT_FOUND.value(), ex.getMessage());
    }



}
