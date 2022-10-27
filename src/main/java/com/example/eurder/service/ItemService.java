package com.example.eurder.service;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.dto.ItemDto;
import org.springframework.stereotype.Service;

import static com.example.eurder.domain.user.Feature.ADDING_NEW_ITEM;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ValidationService validationService;
    private final SecurityService securityService;

    public ItemService(ItemRepository itemRepository, UserRepository userRepository, ValidationService validationService, SecurityService securityService) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.validationService = validationService;
        this.securityService = securityService;
    }

    public void createANewItemInItemRepository(String authorization, ItemDto itemDto) {
        securityService.validateAuthorization(authorization,ADDING_NEW_ITEM);
        validationService.validateInputOfitemDto(itemDto, "name");
        validationService.validateInputOfitemDto(itemDto, "description");
        validationService.validateInputOfitemDto(itemDto, "price");
        validationService.validateInputOfitemDto(itemDto, "amount");
        itemRepository.addNewItem(itemDto);

    }

}
