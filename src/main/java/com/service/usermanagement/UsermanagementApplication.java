package com.service.usermanagement;

import com.service.usermanagement.models.Group;
import com.service.usermanagement.models.Role;
import com.service.usermanagement.models.User;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@MappedTypes({User.class, Group.class, Role.class})
@MapperScan("com.service.usermanagement.repositories")
@SpringBootApplication
public class UsermanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsermanagementApplication.class, args);
    }


}
