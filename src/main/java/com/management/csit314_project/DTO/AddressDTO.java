package com.management.csit314_project.DTO;


import com.management.csit314_project.DTO.UserDTO.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO implements Serializable {
    private Integer id;
    private String bname;
    private String street;
    private String suburb;
    private String state;
    private String postCode;
    private UserDTO userOwner; // Assuming you have a UserDTO class
    private RestaurantDTO restaurantOwner; // Assuming you have a Restaurant class
}
