package com.management.csit314_project.DTO;

import com.management.csit314_project.Model.Type.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO implements Serializable {
    private Integer id;
    private String itemName;
    private String description;
    private boolean isAvailable;
    private Long price;
    private ItemCategory itemCategory;
    private RestaurantDTO restaurant;

}

