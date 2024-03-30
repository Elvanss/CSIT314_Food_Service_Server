package com.management.csit314_project.Security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class JwtGenerated {
    private String accessToken;
    //date expired
    private Date expiredIn;
}
