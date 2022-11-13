package com.example.eurder.service.validation;

import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.repositories.UserRepository;
import com.example.eurder.dto.ItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationItemService {
    public static final int MINIMUM_PRICE_VALUE = 1;
    public static final int MINIMUM_AMOUNT_VALUE = 1;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CustomMessageService customMessageService;
    private final Logger logger = LoggerFactory.getLogger(ValidationItemService.class);


    public ValidationItemService(UserRepository userRepository, ItemRepository itemRepository, CustomMessageService customMessageService) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.customMessageService = customMessageService;
    }
    public void validateCorrectInput(ItemDto itemDto) {
        validateAmountOfitemDto(itemDto, "Amount");
        validatePriceOfitemDto(itemDto, "Price");
        validateDescriptionOfitemDto(itemDto, "Description");
        validateNameOfitemDto(itemDto, "Name");
    }


    public void validateAmountOfitemDto(ItemDto itemDto, String amount) {
        if (itemDto.getAmount() < MINIMUM_AMOUNT_VALUE)
            throw new IllegalArgumentException(amount + customMessageService.mustBeHigherThanZeroMessage());
    }

    public void validatePriceOfitemDto(ItemDto itemDto, String price) {
        if (itemDto.getPrice() < MINIMUM_PRICE_VALUE)
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

    public void validateIfItemExist(String itemid) {

        if(!itemRepository.doesItemExist(itemid))throw new IllegalArgumentException("Item does not exist");
    }



}
