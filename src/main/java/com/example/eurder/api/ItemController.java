package com.example.eurder.api;

import com.example.eurder.domain.item.Item;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.service.OrderService;
import com.example.eurder.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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
    public void addANewItem( @RequestHeader String authorization, @RequestBody ItemDto itemDto) {
        logger.info("Create a new Item in itemRepository");
        itemService.createANewItemInItemRepository(itemDto,authorization);
    }

    @PatchMapping(path="/{itemID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateItem(@RequestHeader String authorization, @RequestBody ItemDto itemDto, @PathVariable String itemID){
        itemService.updateThisItem(authorization,itemDto,itemID);
    }

    @GetMapping(path = ("/stock"), produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getStockOverview(@RequestHeader String authorization){
        return itemService.getItemStockOverview(authorization);
    }
}
