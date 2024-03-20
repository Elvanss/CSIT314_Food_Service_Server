package com.management.csit314_project.DTO;

import com.management.csit314_project.DTO.UserDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressDTO {
    private Integer id;
    private UserDTO userDTO;
    private AddressDTO addressDTO;
}
