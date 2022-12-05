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
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Item addANewItem( @RequestBody ItemDto itemDto) {
        return itemService.createANewItemInItemRepository(itemDto);
    }

    @PatchMapping(path="/{itemID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Item updateItem(@RequestBody ItemDto itemDto, @PathVariable String itemID){
        return itemService.updateThisItem(itemDto,itemID);
    }
//security is uit
    @GetMapping(path = ("/stock"), produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Item> getStockOverview(){
        return itemService.getItemStockOverview();
    }
}
