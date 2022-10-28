package com.example.eurder.service.security;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.service.ValidationInputService;
import org.springframework.stereotype.Service;

import static com.example.eurder.domain.user.Feature.ADDING_NEW_ITEM;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ValidationInputService validationInputService;
    private final SecurityService securityService;

    public ItemService(ItemRepository itemRepository, UserRepository userRepository, ValidationInputService validationInputService, SecurityService securityService) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.validationInputService = validationInputService;
        this.securityService = securityService;
    }

    public void createANewItemInItemRepository(String authorization, ItemDto itemDto) {
        securityService.validateAuthorization(authorization,ADDING_NEW_ITEM);
        validationInputService.validateAmountOfitemDto(itemDto, "Amount");
        validationInputService.validatePriceOfitemDto(itemDto,"Price");
        validationInputService.validateDescriptionOfitemDto(itemDto,"Description");
        validationInputService.validateNameOfitemDto(itemDto, "Name");
        itemRepository.addNewItem(itemDto);

    }

}
