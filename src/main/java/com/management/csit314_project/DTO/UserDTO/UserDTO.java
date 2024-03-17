package com.management.csit314_project.DTO.UserDTO;

import com.management.csit314_project.DTO.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private Integer phoneNumber;
    private List<AddressDTO> addressList;


}
