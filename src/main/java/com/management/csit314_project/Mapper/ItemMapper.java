package com.management.csit314_project.Mapper;

import com.management.csit314_project.DTO.ItemDTO;
import com.management.csit314_project.DTO.UserDTO.UserDTO;
import com.management.csit314_project.Model.Item;
import com.management.csit314_project.Model.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class ItemMapper implements Converter<Item, ItemDTO> {

    private final RestaurantMapper restaurantMapper;

    @Override
    public ItemDTO convert(Item item) {

        return new ItemDTO(
                item.getId(),
                item.getItemName(),
                item.getDescription(),
                item.isAvailable(),
                item.getPrice(),
                item.getItemCategory(),
                item.getRestaurantId() != null
                        ? this.restaurantMapper.convert(item.getRestaurantId())
                        : null
        );
    }
}
