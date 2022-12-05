package com.example.eurder.service;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.domain.item.Item;

import com.example.eurder.service.dto.itemDto.ItemDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.service.validation.ValidationItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ValidationItemService validationItemService;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper, ValidationItemService validationItemService) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.validationItemService = validationItemService;
    }

    public Item createANewItemInItemRepository(ItemDto itemDto) {
        validationItemService.validateCorrectInput(itemDto);
        Item item = itemMapper.fromItemDtoToItemWhenCreatingItem(itemDto, UUID.randomUUID().toString());
        itemRepository.save(item);
        return item;
    }



    public Item updateThisItem(ItemDto itemDto, String itemId) {
        validationItemService.validateIfItemExist(itemId);
        validationItemService.validateCorrectInput(itemDto);
        Item item = itemMapper.fromItemDtoToItem(itemDto, itemId);
        itemRepository.save(item);
        return item;
    }

    public List<Item> getItemStockOverview() {
        return itemRepository.findAll();
    }
}
