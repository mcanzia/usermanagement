package com.service.usermanagement.models;

import java.util.List;

/**
 * Model for Group which can contain list of User records
 * @author Michael Canziani
 */
public class Group {

    /** Identification field for Group */
    private Long id;
    /** Name of Group */
    private String name;
    /** List of User records in group */
    private List<User> userList;

    /**
     * Generic constructor for Group
     */
    public Group() {

    }

    /**
     * Overloaded constructor for group provided id, name parameters
     * @param id
     * @param name
     */
    public Group(Long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    /**
     * Retrieve name of Group record
     * @return name of Group record
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of Group record
     * @param name name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieve id of Group record
     * @return id of Group record
     */
    public Long getId() {
        return id;
    }

    /**
     * Set id of Group record
     * @param id id to be set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieve list of User records associated with this Group
     * @return list of User records
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Set list of User records for this Group
     * @param userList list of user records to be set
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
