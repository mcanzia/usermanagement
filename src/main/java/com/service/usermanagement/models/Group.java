package com.service.usermanagement.models;

public class Group {

    private Long id;
    private String name;

    public Group() {

    }

    public Group(Long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
