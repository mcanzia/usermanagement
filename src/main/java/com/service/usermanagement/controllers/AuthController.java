package com.service.usermanagement.controllers;

import com.service.usermanagement.exceptions.DuplicateUserException;
import com.service.usermanagement.models.*;
import com.service.usermanagement.security.utils.JwtTokenUtil;
import com.service.usermanagement.services.SecurityDetailsService;
import com.service.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Service controller to handle authentication requests for registration and logging in users
 * @author Michael Canziani
 */
@RestController
@RequestMapping(path = "/api")
public class AuthController {

    /** Authentication manager used to validate authentication of user details */
    @Autowired
    private AuthenticationManager authenticationManager;
    /** Service used to retrieve security details of user based on username/email */
    @Autowired
    private SecurityDetailsService securityDetailsService;
    /** User service used to retrieve specified users from database */
    @Autowired
    private UserService userService;
    /** JWT utility class used for generating security access token */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /** Used for encrypting passwords */
    @Autowired
    PasswordEncoder encoder;

    /**
     * Auth Controller method for registering user with application
     * @param signUpRequest registering user request details
     * @return response status of callout
     */
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest signUpRequest) {

        try {
            // Find if existing already
            User existingUser = userService.getByUsername(signUpRequest.getEmail());

            if (existingUser != null) {
                existingUser.setPassword(encoder.encode(signUpRequest.getPassword()));
                userService.registerUser(existingUser);
                return new ResponseEntity<User>(HttpStatus.OK);
            } else {
                return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
            }

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }

    }

    /**
     * Auth Controller method for signing in existing user to application
     * @param authRequest logging in user request details
     * @return response status of callout
     */
    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        final UserDetails userDetails = securityDetailsService.loadUserByUsername(authRequest.getEmail());
        final String token = jwtTokenUtil.generateAccessToken(userDetails);
        final User loggedInUser = getLoggedInUser(authRequest.getEmail());

        return ResponseEntity.ok(new AuthResponse(token, loggedInUser));
    }

    /**
     * Auth controller method for retrieving logged in User from DB by username
     * @param username logged in username
     * @return currently logged in User record
     */
    @GetMapping("/auth/{username}")
    public User getLoggedInUser(@PathVariable String username)  {
        return userService.getByUsername(username);
    }

}
