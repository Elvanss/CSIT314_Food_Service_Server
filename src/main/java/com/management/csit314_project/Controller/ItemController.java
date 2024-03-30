package com.management.csit314_project.Controller;

import com.management.csit314_project.DTO.ItemDTO;
import com.management.csit314_project.Service.ItemService;
import com.management.csit314_project.System.Response.Result;
import com.management.csit314_project.System.Response.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Result getAllItemsInRestaurant(@PathVariable int restaurantId) {
        List<ItemDTO> itemDTOs = itemService.getAllItemsInRestaurant(restaurantId);
        return new Result(true, StatusCode.SUCCESS, "Items retrieved successfully", itemDTOs);
    }

    @GetMapping
    public Result getAllItems() {
        List<ItemDTO> itemDTOs = itemService.getAllItems();
        return new Result(true, StatusCode.SUCCESS, "Items retrieved successfully", itemDTOs);
    }

    @GetMapping("/{itemId}")
    public Result getItemById(@PathVariable int itemId) {
        ItemDTO itemDTO = itemService.getItemById(itemId);
        return new Result(true, StatusCode.SUCCESS, "Item retrieved successfully", itemDTO);
    }

    @GetMapping("/restaurant/{restaurantId}/{itemId}")
    public Result getItemByIdOfRestaurant(@PathVariable int restaurantId, @PathVariable int itemId) {
        ItemDTO itemDTO = itemService.getItemByIdOfRestaurant(restaurantId, itemId);
        return new Result(true, StatusCode.SUCCESS, "Item retrieved successfully", itemDTO);
    }

    @PostMapping("/restaurant/{restaurantId}")
    public Result addItemToRestaurant(@PathVariable int restaurantId, @RequestBody ItemDTO itemDTO) {
        ItemDTO addedItemDTO = itemService.addItemToRestaurant(restaurantId, itemDTO);
        return new Result(true, StatusCode.SUCCESS, "Item added successfully", addedItemDTO);
    }

    @PutMapping("/restaurant/{restaurantId}/{itemId}")
    public Result updateItemOfRestaurant(@PathVariable int restaurantId, @PathVariable int itemId, @RequestBody ItemDTO itemDTO) {
        ItemDTO updatedItemDTO = itemService.updateItemOfRestaurant(restaurantId, itemId, itemDTO);
        return new Result(true, StatusCode.SUCCESS, "Item updated successfully", updatedItemDTO);
    }

    @DeleteMapping("/restaurant/{restaurantId}/{itemId}")
    public Result deleteItemOfRestaurant(@PathVariable int restaurantId, @PathVariable int itemId) {
        itemService.deleteItemOfRestaurant(restaurantId, itemId);
        return new Result(true, StatusCode.SUCCESS, "Item deleted successfully");
    }

    @DeleteMapping("/{itemId}")
    public Result deleteItem(@PathVariable int itemId) {
        itemService.deleteItem(itemId);
        return new Result(true, StatusCode.SUCCESS, "Item deleted successfully");
    }

    @DeleteMapping
    public Result deleteAllItems() {
        itemService.deleteAllItems();
        return new Result(true, StatusCode.SUCCESS, "All items deleted successfully");
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public Result deleteAllItemsInRestaurant(@PathVariable int restaurantId) {
        itemService.deleteAllItemsInRestaurant(restaurantId);
        return new Result(true, StatusCode.SUCCESS, "All items in the restaurant deleted successfully");
    }

    @PutMapping("/{itemId}/price/{price}")
    public Result updatePrice(@PathVariable int itemId, @PathVariable long price) {
        itemService.updatePrice(itemId, price);
        return new Result(true, StatusCode.SUCCESS, "Item price updated successfully");
    }
}