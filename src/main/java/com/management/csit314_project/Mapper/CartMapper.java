package com.management.csit314_project.Mapper;

import com.management.csit314_project.DTO.CartDTO;
import com.management.csit314_project.DTO.CartItemResDTO;
import com.management.csit314_project.Model.Cart;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CartMapper implements Converter<Cart, CartDTO> {
    @Override
    public CartDTO convert(Cart cart) {
        return new CartDTO(
                cart.getId(),
                cart.getCartItems().stream().map(cartItem -> new CartItemResDTO(
                        cartItem.getId(),
                        cartItem.getItem(),
                        cartItem.getQuantity(),
                        cartItem.getDateTime()
                )).toList(),
                cart.getTotalPrice(),
                cart.getUser(),
                cart.getCreatedDate()
        );
    }

    public CartDTO cartToCartResDTOInList(Cart cart, List<CartItemResDTO> cartItemResDTOList) {
        CartDTO res = new CartDTO();
        res.setId(cart.getId());
        res.setCartItems(cartItemResDTOList);
        res.setTotalPrice(cart.getTotalPrice());
        res.setUserId(cart.getUser());
        res.setCreatedDate(cart.getCreatedDate());
        return res;
    }
}
