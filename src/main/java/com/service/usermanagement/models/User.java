package com.service.usermanagement.models;

/**
 * Model for User which represents an individual user in the system
 * @author Michael Canziani
 */
public class User {

    /** Id of User record*/
    private Long id;
    /** First name of user */
    private String firstName;
    /** Last name of user */
    private String lastName;
    /** Email/username of user */
    private String email;
    /** Role/Authorization of user*/
    private String role;
    /** Password of user*/
    private String password;
    /** If user is part of a Group, then id of that group, else null*/
    private Long groupId;
    /** If user is part of a Group, then name of that group, else null*/
    private String groupName;

    /**
     * Generic constructor for user record
     */
    public User() {
    }

    /**
     * Creates new User record with the below information
     * @param id unique identifier
     * @param firstName first name of user
     * @param lastName last name of user
     * @param email email/username of user
     * @param role authorities/role of user
     * @param groupId id of group membership
     * @param groupName name of group membership
     */
    public User(Long id, String firstName, String lastName, String email, String role, Long groupId, String groupName) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setRole(role);
        this.setGroupId(id);
        this.setGroupName(groupName);
    }

    /**
     * Retrieve id of User record
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set id of User record
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieve first name of user record
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name of user record
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieve last name of user record
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set last name of user record
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieve email/username of user record
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email/username for user record
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieve role/authority of user record
     * @return role
     */
    public String getRole() { return role; }

    /**
     * Set role/authority of user record
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Retrieve group id of group membership of user record
     * @return group id of group that user is a member of
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * Set group id of group membership of user record
     * @param groupId
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * Retrieve name of group membership of user record
     * @return name of group that user is a member of
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set name of group membership of user record
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Retrieve password of user record
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password for user record
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
