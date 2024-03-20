package com.management.csit314_project.DTO.UserDTO.CategoryDTO;

import com.management.csit314_project.DTO.UserDTO.UserDTO;
import com.management.csit314_project.Model.Type.MembershipType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipUserDTO implements Serializable {
    private Integer userId;
    private Timestamp expiryDateTime;
    private MembershipType membershipType;
}
