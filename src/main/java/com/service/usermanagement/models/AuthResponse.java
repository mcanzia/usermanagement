package com.service.usermanagement.models;

/**
 * Model for AuthResponse when retrieving security responses from validation callouts
 * @author Michael Canziani
 */
public class AuthResponse {

    /** User access token generated by Spring security for user validation*/
    private String token;
    /** Current user granted access to system by Auth callout*/
    private User user;

    /**
     * Constructor for AuthResponse, sets security token and logged in User values
     * @param token security token
     * @param user logged in User
     */
    public AuthResponse(String token, User user) {
        this.setToken(token);
        this.setUser(user);
    }

    /**
     * Retrieve security token for AuthResponse
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * Set security token for AuthResponse
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Retrieve logged in user for AuthResponse
     * @return logged in User
     */
    public User getUser() {
        return user;
    }

    /**
     * Set logged in user for AuthResponse
     * @param user logged in user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
