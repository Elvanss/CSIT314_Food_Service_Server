package com.management.csit314_project.Service;

import com.management.csit314_project.DTO.RestaurantDTO;
import com.management.csit314_project.Mapper.RestaurantMapper;
import com.management.csit314_project.Model.Restaurant;
import com.management.csit314_project.Repository.RestaurantRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    // Get all restaurants
    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDTO> restaurantDTOs = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantDTOs.add(restaurantMapper.convert(restaurant));
        }
        return restaurantDTOs;
    }

    // Get restaurant by ID
    public RestaurantDTO getRestaurantById(int restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        return restaurantMapper.convert(restaurant);
    }

    // Add a new restaurant
    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantMapper.convertToEntity(restaurantDTO);
        restaurantRepository.save(restaurant);
        return restaurantMapper.convert(restaurant);
    }

    // Update an existing restaurant
    public RestaurantDTO updateRestaurant(int restaurantId, RestaurantDTO restaurantDTO) {
        Restaurant existingRestaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));

        existingRestaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        existingRestaurant.setEmail(restaurantDTO.getEmail());
        existingRestaurant.setPhone(restaurantDTO.getPhone());
        existingRestaurant.setCuisine(restaurantDTO.getCuisine());
        existingRestaurant.setOpenTime(restaurantDTO.getOpenTime());
        existingRestaurant.setCloseTime(restaurantDTO.getCloseTime());
        existingRestaurant.setOpened(restaurantDTO.isOpened());
        existingRestaurant.setDescription(restaurantDTO.getDescription());
        // Update address and items if needed

        restaurantRepository.save(existingRestaurant);
        return restaurantMapper.convert(existingRestaurant);
    }

    // Delete a restaurant
    public void deleteRestaurant(int restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        restaurant.removeAllItems();
        restaurantRepository.delete(restaurant);
    }
}