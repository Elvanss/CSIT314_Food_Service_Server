package com.management.csit314_project.DTO;


import com.management.csit314_project.DTO.UserDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements Serializable {
    private Integer id;
    private String bname;
    private String street;
    private String suburb;
    private String state;
    private String postCode;

}
