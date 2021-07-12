package com.service.usermanagement.security.utils;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Utility class used for generating and validating security token
 * @author Michael Canziani
 */
@Component
public class JwtTokenUtil {

    /**
     * Used for signing security token when sending to client and decoding when receiving token back
     * in service request
     */
    private final String jwtSecret = "jwtsecret";

    /**
     * Checks validity of service token received by client in callout
     * @param token security token to check
     * @return true if token is valid, false if not valid
     */
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

    /**
     * Retrieves information about the token itself (like username, expiration date, creation date)
     * @param token security token
     * @return information about security token
     */
    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    /**
     * Used to retrieve username off of security token
     * @param token security token
     * @return username of user trying to validate
     */
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Generates security access token to be sent to clients to include in their HTTP requests
     * Sets token expiration for 24 hours
     * @param user security details of user used for verification
     * @return security token
     */
    public String generateAccessToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername()  )
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24 hours
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
