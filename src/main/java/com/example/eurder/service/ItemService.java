package com.example.eurder.service;

import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.service.security.SecurityService;
import com.example.eurder.service.validation.ValidationItemService;
import org.springframework.stereotype.Service;

import static com.example.eurder.domain.user.Feature.ADDING_NEW_ITEM;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ValidationItemService validationItemService;
    private final SecurityService securityService;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper, ValidationItemService validationItemService, SecurityService securityService) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.validationItemService = validationItemService;
        this.securityService = securityService;
    }

    public void createANewItemInItemRepository(String authorization, ItemDto itemDto) {
        securityService.validateAuthorization(authorization, ADDING_NEW_ITEM);
        validationItemService.validateAmountOfitemDto(itemDto, "Amount");
        validationItemService.validatePriceOfitemDto(itemDto, "Price");
        validationItemService.validateDescriptionOfitemDto(itemDto, "Description");
        validationItemService.validateNameOfitemDto(itemDto, "Name");
        itemRepository.addNewItem(itemMapper.fromDtoToItem(itemDto));
    }

}
