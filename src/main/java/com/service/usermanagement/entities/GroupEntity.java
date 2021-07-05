package com.service.usermanagement.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_groups")
public class GroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String name;


    public GroupEntity() {
    }

    public Long getId() { return this.id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }


}
