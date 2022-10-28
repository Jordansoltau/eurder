package com.example.eurder.service.validation;

import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.dto.ItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationItemService {
    private final UserRepository userRepository;
    private final CustomMessageService customMessageService;
    private final Logger logger = LoggerFactory.getLogger(ValidationItemService.class);


    public ValidationItemService(UserRepository userRepository, CustomMessageService customMessageService) {
        this.userRepository = userRepository;
        this.customMessageService = customMessageService;
    }


    public void validateAmountOfitemDto(ItemDto itemDto, String amount) {
        if (itemDto.getAmount() < 1)
            throw new IllegalArgumentException(amount + customMessageService.mustBeHigherThanZeroMessage());
    }

    public void validatePriceOfitemDto(ItemDto itemDto, String price) {
        if (itemDto.getPrice() < 1)
            throw new IllegalArgumentException(price + customMessageService.mustBeHigherThanZeroMessage());
    }

    public void validateDescriptionOfitemDto(ItemDto itemDto, String description) {
        if (itemDto.getDescription() == null || itemDto.getDescription().isBlank())
            throw new IllegalArgumentException(description + customMessageService.canNotBeEmptyMessage());
    }

    public void validateNameOfitemDto(ItemDto itemDto, String name) {
        if (itemDto.getName() == null || itemDto.getName().isBlank())
            throw new IllegalArgumentException(name + customMessageService.canNotBeEmptyMessage());
    }
}
