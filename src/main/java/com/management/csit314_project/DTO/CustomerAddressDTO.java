package com.management.csit314_project.DTO;

import com.management.csit314_project.DTO.UserDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerAddressDTO {
    private Integer id;
    private UserDTO userDTO;
    private AddressDTO addressDTO;
}
