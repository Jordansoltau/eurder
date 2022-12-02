package com.example.eurder.service;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.domain.item.Item;

import com.example.eurder.service.dto.itemDto.ItemDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.service.security.SecurityService;
import com.example.eurder.service.validation.ValidationItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.eurder.domain.user.Feature.ADDING_NEW_ITEM;
import static com.example.eurder.domain.user.Feature.ADMIN;

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

    public Item createANewItemInItemRepository(ItemDto itemDto, String authorization) {
        securityService.validateAuthorization(authorization, ADDING_NEW_ITEM);
        validationItemService.validateCorrectInput(itemDto);
        Item item = itemMapper.fromItemDtoToItemWhenCreatingItem(itemDto, UUID.randomUUID().toString());
        itemRepository.save(item);
        return item;
    }



    public Item updateThisItem(String authorization, ItemDto itemDto, String itemId) {
        securityService.validateAuthorization(authorization,ADMIN);
        validationItemService.validateIfItemExist(itemId);
        validationItemService.validateCorrectInput(itemDto);
        Item item = itemMapper.fromItemDtoToItem(itemDto, itemId);
        itemRepository.save(item);
        return item;
    }

    public List<Item> getItemStockOverview() {
//        securityService.validateAuthorization(authorization,ADMIN);
        return itemRepository.findAll();
    }
}
