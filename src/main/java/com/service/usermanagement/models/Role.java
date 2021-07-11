package com.service.usermanagement.models;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    public static final String USER_ADMIN = "ADMIN";
    public static final String USER_BASIC = "BASIC";

    private String authority;

    public Role() {
    }

    public Role(String authority) {
        if (authority.equals("ADMIN")) {
            setAuthority(USER_ADMIN);
        } else if (authority.equals("BASIC")) {
            setAuthority(USER_BASIC);
        }
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
