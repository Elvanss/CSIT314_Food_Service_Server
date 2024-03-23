package com.management.csit314_project.DTO;

import com.management.csit314_project.Model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Integer id;
    private List<CartItem> cartItems = new ArrayList<>();
    private Long totalPrice;
}
