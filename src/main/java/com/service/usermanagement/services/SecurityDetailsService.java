package com.service.usermanagement.services;

import com.service.usermanagement.models.Role;
import com.service.usermanagement.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service class used to retrieve security details about user
 * @author Michael Canziani
 */
@Service("securityDetailsService")
public class SecurityDetailsService implements UserDetailsService {

    /** Used to retrieve User record from database based on username/email */
    private UserService userService;

    /**
     * Constructor for SecurityDetailsService, sets dependencies for userService to be injected
     * @param userService
     */
    public SecurityDetailsService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Built-in security method to retrieve security details from user based on their username/email
     * @param username username to find user by (we use email for this application)
     * @return security user details
     * @throws UsernameNotFoundException if user details not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        ArrayList<Role> authorities = new ArrayList<Role>();
        authorities.add(new Role(user.getRole().toUpperCase()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
