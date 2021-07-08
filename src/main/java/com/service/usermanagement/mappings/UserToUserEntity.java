package com.service.usermanagement.mappings;

import com.service.usermanagement.entities.GroupEntity;
import com.service.usermanagement.entities.UserEntity;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserEntity implements Converter<User, UserEntity> {
    @Override
    public UserEntity convert(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setRole(user.getRole());
        if (user.getGroupId() != -1) {
            GroupEntity groupEntity = new GroupEntity();
            groupEntity.setId(user.getGroupId());
            groupEntity.setName(user.getGroupName());
            userEntity.setGroupEntity(groupEntity);
        }
        return userEntity;
    }
}
