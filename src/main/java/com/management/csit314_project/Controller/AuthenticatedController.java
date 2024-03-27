package com.management.csit314_project.Controller;

import com.management.csit314_project.DTO.UserDTO.UserDTO;
import com.management.csit314_project.Service.RestaurantService;
import com.management.csit314_project.Service.UserSer.UserService;
import com.management.csit314_project.System.Response.Result;
import com.management.csit314_project.System.Response.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services/authenticated")
public class AuthenticatedController {

    private UserService userService;
//    private RestaurantService restaurantService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//    @Autowired
//    public void setRestaurantService(RestaurantService restaurantService) {
//        this.restaurantService = restaurantService;
//    }

    /*
    * Area: User Profile with API Services
    * */

    // Show the user profile by username with authentication
    @GetMapping("/profile")
    public Result getUserProfile (UserDetails userDetail, UserDTO userDTO) {
        String usernameFilter = userDetail.getUsername();
        userDTO = userService.getUserInfo(usernameFilter);
        return new Result(true, StatusCode.SUCCESS, "Get User Profile Successfully", userDTO);
    }

    // Update the user profile by username with authentication
    @GetMapping("/profile/update")
    public Result updateUserProfile (UserDetails userDetail, UserDTO userDTO) {

        String usernameFilter = userDetail.getUsername();
        UserDTO updatedField = new UserDTO();
        updatedField.setFirstName(userDTO.getFirstName());
        updatedField.setLastName(userDTO.getLastName());
        updatedField.setEmail(userDTO.getEmail());

        userDTO = userService.updateUserProfile(usernameFilter, updatedField);
        return new Result(true, StatusCode.SUCCESS, "Update User Profile Successfully", userDTO);
    }

    /*
     * Area: Restaurant Profile with API Services
     * */



}
