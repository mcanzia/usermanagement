package com.service.usermanagement.repositories;

import com.service.usermanagement.entities.UserEntity;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.models.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

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

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "groupId", column = "group_id"),
    })
    public User findById(Long id);

    @Insert("INSERT INTO users(first_name,last_name,email,role) " +
            "VALUES (#{firstName}, #{lastName}, #{email}, #{role})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public void insert(User user);

    @Update("UPDATE users SET group_id=#{groupId} where id=#{id}")
    public int update(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    public int deleteById(Long id);


    /*
    @Query("SELECT u FROM UserEntity u WHERE u.email=:email")
    UserEntity findByEmail(@Param("email") String email);

    @Query("SELECT u FROM UserEntity u where u.groupEntity.groupId = :groupId")
    List<UserEntity> findByGroup(@Param("groupId") Long groupId);
     */

}
