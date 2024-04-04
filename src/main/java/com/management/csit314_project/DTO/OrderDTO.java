package com.management.csit314_project.DTO;

import com.management.csit314_project.DTO.UserDTO.UserDTO;
import com.management.csit314_project.Model.Address;
import com.management.csit314_project.Model.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Integer id;
    private Address address;
    private Date createdAt;
    private Date deliveredTime;
    private Long totalPrice;
    private String status;
    private User userId;
    private List<CartItemResDTO> cartItems;

}