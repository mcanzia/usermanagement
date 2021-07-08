package com.service.usermanagement.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="user_groups")
public class GroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @OneToMany(mappedBy = "groupEntity")
    private List<UserEntity> userList;

    public GroupEntity() {
    }

    public Long getId() { return this.groupId; }

    public void setId(Long id) { this.groupId = id; }

    public String getName() { return this.groupName; }

    public void setName(String name) { this.groupName = name; }


    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }
}
