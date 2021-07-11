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

@RestController
@RequestMapping(path = "/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecurityDetailsService securityDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest signUpRequest) {

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

    @GetMapping("/auth/{username}")
    public User getLoggedInUser(@PathVariable String username)  {
        return userService.getByUsername(username);
    }

}
