package com.management.csit314_project.Mapper;

import com.management.csit314_project.DTO.CartDTO;
import com.management.csit314_project.Model.Cart;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartMapper implements Converter<Cart, CartDTO> {
    @Override
    public CartDTO convert(Cart cart) {
        return new CartDTO(
                cart.getId(),
                cart.getCartItems(),
                cart.getTotalPrice()
        );
    }
}
