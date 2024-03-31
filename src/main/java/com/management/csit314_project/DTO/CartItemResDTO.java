package com.management.csit314_project.DTO;

import com.management.csit314_project.Model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResDTO {
    private Integer id;
    private Item item;
    private Integer quantity;
    private Date date;

}
