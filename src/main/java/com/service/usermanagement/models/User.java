package com.service.usermanagement.models;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private Long groupId;
    private String groupName;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email, String role, Long groupId, String groupName) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setRole(role);
        this.setGroupId(id);
        this.setGroupName(groupName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
