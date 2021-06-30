package com.service.usermanagement.mappings;


import com.service.usermanagement.entities.UserEntity;
import com.service.usermanagement.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUser implements Converter<UserEntity, User> {
    @Override
    public User convert(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        return user;
    }
}