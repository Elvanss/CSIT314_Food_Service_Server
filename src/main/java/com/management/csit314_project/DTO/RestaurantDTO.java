package com.management.csit314_project.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO implements Serializable {
    private Integer id;
    private String email;
    private String phone;
    private String cuisine;
    private Time openTime;
    private Time closeTime;
    private boolean isOpened;
    private String description;
    private AddressDTO address;
    private Integer itemList;

    // Constructor, getters, and setters
}

