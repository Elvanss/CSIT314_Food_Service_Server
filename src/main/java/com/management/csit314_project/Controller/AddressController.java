package com.management.csit314_project.Controller;

import com.management.csit314_project.DTO.AddressDTO;
import com.management.csit314_project.DTO.CustomerAddressDTO;
import com.management.csit314_project.DTO.RestaurantAddressDTO;
import com.management.csit314_project.Service.AddressService;
import com.management.csit314_project.System.Response.Result;
import com.management.csit314_project.System.Response.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/all")
// Display all addresses (both user and restaurant)
    public Result getAllAddresses() {
        List<AddressDTO> addresses = addressService.getAllAddresses();
        return new Result(true, StatusCode.SUCCESS, "All addresses are displayed successfully", addresses);
    }

    @GetMapping("/users")
// Display all user addresses
    public Result getAllUserAddresses() {
        List<CustomerAddressDTO> userAddresses = addressService.getAllUserAddresses();
        return new Result(true, StatusCode.SUCCESS, "User addresses are displayed successfully", userAddresses);
    }

    @GetMapping("/restaurants")
// Display all restaurant addresses
    public Result getAllRestaurantAddresses() {
        List<RestaurantAddressDTO> restaurantAddresses = addressService.getAllRestaurantAddresses();
        return new Result(true, StatusCode.SUCCESS, "Restaurant addresses are displayed successfully", restaurantAddresses);
    }

}
