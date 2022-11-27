package com.example.eurder.service.validation;


import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.dto.ItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationItemService {
    public static final int MINIMUM_PRICE_VALUE = 1;
    public static final int MINIMUM_AMOUNT_VALUE = 1;
    private final ItemRepository itemRepository;
    private final CustomMessageService customMessageService;
    private final Logger logger = LoggerFactory.getLogger(ValidationItemService.class);


    public ValidationItemService(ItemRepository itemRepository, CustomMessageService customMessageService) {
        this.itemRepository = itemRepository;
        this.customMessageService = customMessageService;
    }

    public void validateCorrectInput(ItemDto itemDto) {
        validateAmountOfItemDto(itemDto, "Amount");
        validatePriceOfItemDto(itemDto, "Price");
        validateDescriptionOfItemDto(itemDto, "Description");
        validateNameOfItemDto(itemDto, "Name");
    }


    public void validateAmountOfItemDto(ItemDto itemDto, String amount) {
        if (itemDto.getAmount() < MINIMUM_AMOUNT_VALUE)
            throw new IllegalArgumentException(amount + customMessageService.mustBeHigherThanZeroMessage());
        logger.info("Amount to purchase " + customMessageService.mustBeHigherThanZeroMessage());
    }

    public void validatePriceOfItemDto(ItemDto itemDto, String price) {
        if (itemDto.getPrice() < MINIMUM_PRICE_VALUE)
            throw new IllegalArgumentException(price + customMessageService.mustBeHigherThanZeroMessage());
        logger.info("price " + customMessageService.mustBeHigherThanZeroMessage());
    }

    public void validateDescriptionOfItemDto(ItemDto itemDto, String description) {
        if (itemDto.getDescription() == null || itemDto.getDescription().isBlank())
            throw new IllegalArgumentException(description + customMessageService.canNotBeEmptyMessage());
        logger.info("Description " + customMessageService.canNotBeEmptyMessage());
    }

    public void validateNameOfItemDto(ItemDto itemDto, String name) {
        if (itemDto.getName() == null || itemDto.getName().isBlank())
            throw new IllegalArgumentException(name + customMessageService.canNotBeEmptyMessage());
        logger.info("name " + customMessageService.canNotBeEmptyMessage());
    }

    public void validateIfItemExist(String itemId) {

        if (doesItemExist(itemId)) throw new IllegalArgumentException("Item does not exist");
        logger.info("Item does not exist");
    }

    private boolean doesItemExist(String itemId) {
        return itemRepository.findById(itemId).isEmpty();
    }


}
