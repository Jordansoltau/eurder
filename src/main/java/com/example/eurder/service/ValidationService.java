package com.example.eurder.service;

import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.domain.user.Feature;
import com.example.eurder.domain.user.User;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.exceptions.UnauthorizatedException;
import com.example.eurder.exceptions.UnknownPersonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
private final UserRepository userRepository;
private final Logger logger = LoggerFactory.getLogger(ValidationService.class);

    public ValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void validateInputOfitemDto(ItemDto itemDto, String field, ItemService itemService) {
        if (itemDto.getName() == null || itemDto.getName().isBlank())
            throw new IllegalArgumentException(field + canNotBeEmptyMessage());
        if (itemDto.getDescription() == null || itemDto.getDescription().isBlank())
            throw new IllegalArgumentException(field + canNotBeEmptyMessage());
        if (itemDto.getPrice() < 1) throw new IllegalArgumentException(field + mustBeHigherThanZeroMessage());
        if (itemDto.getAmount() < 1) throw new IllegalArgumentException(field + mustBeHigherThanZeroMessage());
    }

    private String mustBeHigherThanZeroMessage() {
        return " must be higher than Zero!";
    }
    private String canNotBeEmptyMessage() {
        return " can not be empty!";
    }


}
