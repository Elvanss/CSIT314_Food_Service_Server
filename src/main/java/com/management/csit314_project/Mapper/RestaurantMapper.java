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
}
