package com.management.csit314_project.DTO;

import lombok.Getter;
import lombok.Setter;

// Data Transfer Object for Login
@Getter
@Setter
public class LoginDTO {
    private String usernameOrEmail;
    private String password;
}
