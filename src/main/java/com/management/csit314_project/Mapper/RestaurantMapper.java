package com.management.csit314_project.Mapper;

import com.management.csit314_project.DTO.RestaurantDTO;
import com.management.csit314_project.Model.Address;
import com.management.csit314_project.Model.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RestaurantMapper implements Converter<Restaurant, RestaurantDTO> {

    private final AddressMapper addressMapper;

    @Override
    public RestaurantDTO convert(Restaurant restaurant) {
        return new RestaurantDTO(restaurant.getId(),
                restaurant.getRestaurantName(),
                restaurant.getEmail(),
                restaurant.getPhone(),
                restaurant.getCuisine(),
                restaurant.getOpenTime(),
                restaurant.getCloseTime(),
                restaurant.isOpened(),
                restaurant.getDescription(),
                restaurant.getAddress() != null
                        ? this.addressMapper.convert(restaurant.getAddress())
                        : null,
                restaurant.getNumberOfItems()); //Shows the number of items list
    }

    public Restaurant convertToEntity(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDTO.getId());
        restaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        restaurant.setEmail(restaurantDTO.getEmail());
        restaurant.setPhone(restaurantDTO.getPhone());
        restaurant.setCuisine(restaurantDTO.getCuisine());
        restaurant.setOpenTime(restaurantDTO.getOpenTime());
        restaurant.setCloseTime(restaurantDTO.getCloseTime());
        restaurant.setOpened(restaurantDTO.isOpened());
        restaurant.setDescription(restaurantDTO.getDescription());
        return restaurant;
    }
}
