package com.example.eurder.api;

import com.example.eurder.dto.ItemDto;
import com.example.eurder.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("items")
@RestController
public class ItemController {
    private final ItemService itemService;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addANewItem(@RequestHeader String authorization, @RequestBody ItemDto itemDto) {
        itemService.createANewItemInItemRepository(authorization, itemDto);
    }
}
