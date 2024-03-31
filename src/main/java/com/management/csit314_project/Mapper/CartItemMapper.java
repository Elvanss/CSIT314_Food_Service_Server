package com.management.csit314_project.Mapper;

import com.management.csit314_project.DTO.CartItemResDTO;
import com.management.csit314_project.Model.CartItem;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CartItemMapper implements Converter<CartItem, CartItemResDTO> {

    @Override
    public CartItemResDTO convert(CartItem cartItem) {
        return new CartItemResDTO(
                cartItem.getId(),
                cartItem.getItem(),
                cartItem.getQuantity(),
                cartItem.getDateTime()
        );
    }

    public List<CartItemResDTO> listCartItemsToListCartItems(List<CartItem> list) {
        if (list == null) {
            return null;
        }
        return list
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
