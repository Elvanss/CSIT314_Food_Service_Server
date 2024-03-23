package com.management.csit314_project.Mapper;

import com.management.csit314_project.DTO.CartItemDTO;
import com.management.csit314_project.Model.CartItem;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartItemMapper implements Converter<CartItem, CartItemDTO> {

    @Override
    public CartItemDTO convert(CartItem cartItem) {
        return new CartItemDTO(
                cartItem.getId(),
                cartItem.getItem(),
                cartItem.getQuantity()
        );
    }
}
