package com.service.usermanagement.repositories;

import com.service.usermanagement.models.Role;
import com.service.usermanagement.models.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRepository {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "groupName", column="group_id", javaType=String.class, one=@One(select="selectGroupName"))
    })
    List<User> findAll();

    @Select("SELECT group_name FROM user_groups WHERE group_id=#{group_id}")
    String selectGroupName(String group_id);


    @Select("SELECT * FROM users WHERE id=#{id}")
    @Results({
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "groupName", column="group_id", javaType=String.class, one=@One(select="selectGroupName"))
    })
    public User findById(Long id);

    @Select("SELECT * FROM users WHERE email = #{email}")
    @Results({
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "groupId", column = "group_id"),
    })
    public User findByUsername(String email);

    @Insert("INSERT INTO users(first_name,last_name,email,role_id) " +
            "VALUES (#{firstName}, #{lastName}, #{email}, #{role})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public void insert(User user);

    @Update("UPDATE users SET group_id=#{groupId} where id=#{id}")
    public int update(User user);

    @Update("UPDATE users SET password=#{password} where id=#{id}")
    public int registerUser(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    public int deleteById(Long id);

}
