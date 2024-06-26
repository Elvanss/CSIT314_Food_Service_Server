package com.management.csit314_project.DTO;

import lombok.*;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private String refreshToken;
    private String accessToken;
    private Date expiredIn;
    private List<String> roles;
    private Integer cartId;
    private Integer userId;
    private int quantity;
}
