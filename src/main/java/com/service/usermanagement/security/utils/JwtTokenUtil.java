package com.service.usermanagement.security.utils;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private final String jwtSecret = "jwtsecret";

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Empty JWT claims string: " + e.getMessage());
        }
        return false;
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject().split(",")[1];
    }

    public String generateAccessToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername()  )
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24 hours
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
