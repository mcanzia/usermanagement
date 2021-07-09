package com.service.usermanagement.mappings;

import com.service.usermanagement.models.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users")
    List<User> findAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    public User findById(long id);

    @Insert("INSERT INTO users(first_name,last_name,email,role) values(#{")
    void insert(User user);

    @Delete("DELETE FROM employees WHERE id = #{id}")
    public int deleteById(long id);


}
