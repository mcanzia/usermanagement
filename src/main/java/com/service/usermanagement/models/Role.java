package com.service.usermanagement.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * Role class used for Spring security to define user authorization to different parts of the application
 * @author Michael Canziani
 */
public class Role implements GrantedAuthority {

    /** Read-only value for ADMIN role */
    public static final String USER_ADMIN = "ADMIN";
    /** Read-only value for BASIC role */
    public static final String USER_BASIC = "BASIC";
    /** Role type */
    private String authority;

    /**
     * Generic constructor for Role record
     */
    public Role() {
    }

    /**
     * Creates a new Role record and sets authority field based on parameter
     * @param authority type of role to be set
     */
    public Role(String authority) {
        if (authority.equals("ADMIN")) {
            setAuthority(USER_ADMIN);
        } else if (authority.equals("BASIC")) {
            setAuthority(USER_BASIC);
        }
    }

    /**
     * Returns current role type of record
     * @return role type
     */
    @Override
    public String getAuthority() {
        return authority;
    }

    /**
     * Sets role type of record
     * @param authority role type to be set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
