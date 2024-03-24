package com.management.csit314_project.Service;

import com.management.csit314_project.Mapper.RestaurantMapper;

public class RestaurantService {

    private final RestaurantService restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantService restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }




}
