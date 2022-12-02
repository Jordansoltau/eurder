package com.example.eurder.api;

import com.example.eurder.domain.item.Item;
import com.example.eurder.service.dto.itemDto.ItemDto;
import com.example.eurder.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("items")
@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Item addANewItem( @RequestHeader String authorization, @RequestBody ItemDto itemDto) {
        return itemService.createANewItemInItemRepository(itemDto,authorization);
    }

    @PatchMapping(path="/{itemID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Item updateItem(@RequestHeader String authorization, @RequestBody ItemDto itemDto, @PathVariable String itemID){
        return itemService.updateThisItem(authorization,itemDto,itemID);
    }
//security is uit
    @GetMapping(path = ("/stock"), produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getStockOverview(){
        return itemService.getItemStockOverview();
    }
}
