package com.management.csit314_project.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RestaurantAddressDTO {
    private Integer id;
    private RestaurantDTO restaurantDTO;
    private AddressDTO addressDTO;

}
