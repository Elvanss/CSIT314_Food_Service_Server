package com.management.csit314_project.Service;

import com.management.csit314_project.Mapper.RestaurantMapper;
import com.management.csit314_project.Repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private final RestaurantMapper restaurantMapper;
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantMapper = restaurantMapper;
        this.restaurantRepository = restaurantRepository;

    }





}
