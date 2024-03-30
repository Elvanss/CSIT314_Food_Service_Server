package com.management.csit314_project.Service;

import com.management.csit314_project.DTO.ItemDTO;
import com.management.csit314_project.Mapper.ItemMapper;
import com.management.csit314_project.Model.Item;
import com.management.csit314_project.Model.Restaurant;
import com.management.csit314_project.Repository.ItemRepository;
import com.management.csit314_project.Repository.RestaurantRepository;
import com.management.csit314_project.Repository.UserRepo.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public ItemService(ItemRepository itemRepository,
                       ItemMapper itemMapper,
                       RestaurantRepository restaurantRepository,
                       UserRepository userRepository) {

        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }


    // Show all the items in specific restaurant
    public List<ItemDTO> getAllItemsInRestaurant(int restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        List<Item> items = itemRepository.findByRestaurant(restaurant);
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : items) {
            itemDTOs.add(itemMapper.convert(item));
        }
        return itemDTOs;
    }

    // Show all the items in the system
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : items) {
            itemDTOs.add(itemMapper.convert(item));
        }
        return itemDTOs;
    }

    // Show the item by id
    public ItemDTO getItemById(int itemId) {
        Item item = itemRepository
                .findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
        return itemMapper.convert(item);
    }

    // Show the item by id of restaurant
    public ItemDTO getItemByIdOfRestaurant(int restaurantId, int itemId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        Item item = itemRepository
                .findByIdAndRestaurantId(itemId, restaurant)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
        return itemMapper.convert(item);
    }

    // Add a new item to that restaurant
    public ItemDTO addItemToRestaurant(int restaurantId, ItemDTO itemDTO) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        Item item = itemMapper.convertToEntity(itemDTO);
        item.setRestaurantId(restaurant);
        item.setId(itemDTO.getId());
        item.setItemName(itemDTO.getItemName());
        item.setDescription(itemDTO.getDescription());
        item.setAvailable(itemDTO.isAvailable());
        item.setItemCategory(itemDTO.getItemCategory());
        itemRepository.save(item);
        return itemMapper.convert(item);
    }

    // Update the item by id of restaurant
    public ItemDTO updateItemOfRestaurant(int restaurantId, int itemId, ItemDTO itemDTO) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        Item item = itemRepository
                .findByIdAndRestaurantId(itemId, restaurant)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
        item.setId(itemDTO.getId());
        item.setItemName(itemDTO.getItemName());
        item.setDescription(itemDTO.getDescription());
        item.setAvailable(itemDTO.isAvailable());
        item.setItemCategory(itemDTO.getItemCategory());
        itemRepository.save(item);
        return itemMapper.convert(item);
    }

    // Delete the item by id of restaurant
    public void deleteItemOfRestaurant(int restaurantId, int itemId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        Item item = itemRepository
                .findByIdAndRestaurantId(itemId, restaurant)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
        itemRepository.delete(item);
    }

    // Delete the item by id
    public void deleteItem(int itemId) {
        Item item = itemRepository
                .findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
        itemRepository.delete(item);
    }

    // Delete all the items in the system
    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

    // Delete all the items in the restaurant
    public void deleteAllItemsInRestaurant(int restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        List<Item> items = itemRepository.findByRestaurant(restaurant);
        for (Item item : items) {
            itemRepository.delete(item);
        }
    }

    // Update the price when the items sale
    public void updatePrice(int itemId, long price) {
        Item item = itemRepository
                .findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
        item.setPrice(price);
        itemRepository.save(item);
    }

}