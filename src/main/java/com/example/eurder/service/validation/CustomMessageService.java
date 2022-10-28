package com.example.eurder.service.validation;

import org.springframework.stereotype.Service;

@Service
public class CustomMessageService {

    public String mustBeValid() {
        return "must be valid";
    }

    public String mustBeHigherThanZeroMessage() {
        return " must be higher than Zero!";
    }

    public String canNotBeEmptyMessage() {
        return " can not be empty!";
    }
}
