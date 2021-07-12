package com.service.usermanagement.models;

/**
 * Model for AuthRequests used for security validation
 * @Author Michael Canziani
 */
public class AuthRequest {

    /** Email/username used to identify a user */
    private String email;
    /** Password used to secure a user's account */
    private String password;

    /**
     * Retrieve password for AuthRequest
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password for AuthRequest
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set email for AuthRequest
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieve email for AuthRequest
     * @return email
     */
    public String getEmail() {
        return email;
    }
}
