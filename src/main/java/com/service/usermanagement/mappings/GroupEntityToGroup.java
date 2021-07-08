package com.service.usermanagement.mappings;

import com.service.usermanagement.entities.GroupEntity;
import com.service.usermanagement.entities.UserEntity;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class GroupEntityToGroup implements Converter<GroupEntity, Group> {

    public Group convert(GroupEntity groupEntity) {
        Group newGroup = new Group();
        newGroup.setId(groupEntity.getId());
        newGroup.setName(groupEntity.getName());
        if (groupEntity.getUserList() != null) {
            List<User> userList = new ArrayList<User>();
            UserEntityToUser userConverter = new UserEntityToUser();
            for (UserEntity ue : groupEntity.getUserList()) {
                userList.add(userConverter.convert(ue));
            }
            newGroup.setUserList(userList);
        }
        return newGroup;
    }
}
