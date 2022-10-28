package com.example.eurder.service.security;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.service.validation.ValidationItemService;
import org.springframework.stereotype.Service;

import static com.example.eurder.domain.user.Feature.ADDING_NEW_ITEM;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ValidationItemService validationItemService;
    private final SecurityService securityService;

    public ItemService(ItemRepository itemRepository, UserRepository userRepository, ValidationItemService validationItemService, SecurityService securityService) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.validationItemService = validationItemService;
        this.securityService = securityService;
    }

    public void createANewItemInItemRepository(String authorization, ItemDto itemDto) {
        securityService.validateAuthorization(authorization, ADDING_NEW_ITEM);
        validationItemService.validateAmountOfitemDto(itemDto, "Amount");
        validationItemService.validatePriceOfitemDto(itemDto, "Price");
        validationItemService.validateDescriptionOfitemDto(itemDto, "Description");
        validationItemService.validateNameOfitemDto(itemDto, "Name");
        itemRepository.addNewItem(itemDto);

    }

}
