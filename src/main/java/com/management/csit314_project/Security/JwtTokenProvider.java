package com.management.csit314_project.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.duration}")
    private long jwtExpirationDate;

    public JwtGenerated generatedToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        Date expiredIn = new Date(System.currentTimeMillis()+jwtExpirationDate*1000);
        byte[] keyBytes = jwtSecret.getBytes();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

        return JwtGenerated.builder()
                .accessToken(Jwts.builder()
                        .setClaims(claims)
                        .setSubject(userDetails.getUsername())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(expiredIn)
//                        .signWith(signatureAlgorithm, secretKey)
                        .signWith(signatureAlgorithm, jwtSecret)
                        .compact())
                .expiredIn(expiredIn)
                .build();
    }
    public Claims getClaimsFromToken(String token){
        try{
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        }catch(ExpiredJwtException e){
            log.error(e.getMessage(), e);
            return null;
        }
    }
    public boolean validateToken(String token){
        try{
            Jwts
                    .parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;

        }catch(MalformedJwtException ex){
            log.error("Invalid JWT Token" + ex.getMessage(), ex);
        }catch(ExpiredJwtException ex){
            log.error("Expired JWT Token "+ex.getMessage(), ex);
        }catch(UnsupportedJwtException ex){
            log.error("Unsupported JWT Token "+ex.getMessage(), ex);
        }catch(IllegalArgumentException ex){
            log.error("JWT claims string is empty "+ex.getMessage(), ex);
        }
        return false;
    }

}
