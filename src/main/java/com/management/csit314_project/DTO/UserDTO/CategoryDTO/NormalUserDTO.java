package com.management.csit314_project.DTO.UserDTO.CategoryDTO;

import com.management.csit314_project.DTO.UserDTO.UserDTO;
import com.management.csit314_project.Model.Type.MembershipType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class NormalUserDTO implements Serializable {
    private UserDTO userId;
    private MembershipType membershipType;

}

