package com.service.usermanagement.repositories;

import com.service.usermanagement.entities.GroupEntity;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.models.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper
public interface GroupRepository {

    @Select("SELECT * FROM user_groups")
    @Results(value = {
            @Result(property="id", column = "group_id"),
            @Result(property="name", column = "group_name"),
            @Result(property="userList", column="group_id", javaType= List.class, many=@Many(select="selectUsers"))
    })
    List<Group> findAll();

    @Select("SELECT * FROM user_groups WHERE group_id = #{id}")
    @Results(value = {
            @Result(property="id", column = "group_id"),
            @Result(property="name", column = "group_name"),
            @Result(property="userList", column="group_id", javaType= List.class, many=@Many(select="selectUsers"))
    })
    public Group findById(Long id);

    @Select("SELECT * FROM users WHERE group_id = #{group_id}")
    @Results(value={
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "groupId", column = "group_id"),
    })
    List<User> selectUsers(String group_Id);


    @Insert("INSERT INTO user_groups(group_name) VALUES (#{name})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="group_id")
    public void insert(Group group);

    @Update("UPDATE user_groups SET group_name=#{name} where group_id=#{id}")
    public int update(Group group);

    @Delete("DELETE FROM user_groups WHERE group_id=#{id}")
    public int deleteById(Long id);
}
