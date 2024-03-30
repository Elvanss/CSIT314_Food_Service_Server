package com.management.csit314_project.DTO.UserDTO;

import com.management.csit314_project.DTO.AddressDTO;
import com.management.csit314_project.DTO.UserDTO.CategoryDTO.MembershipUserDTO;
import com.management.csit314_project.Model.User.Category.MembershipUser;
import com.management.csit314_project.Model.User.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    @NotEmpty(message = "username is required.")
    private String userName;
    @NotEmpty(message = "password is required.")
    private String password;
    @NotEmpty(message = "email is required.")
    private String email;
    private Long phoneNumber;
    private boolean isMember;
    private MembershipUserDTO membershipUserDTO;
    private Set<Role> roles;


}
