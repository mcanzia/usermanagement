package com.service.usermanagement.mappings;

import com.service.usermanagement.entities.GroupEntity;
import com.service.usermanagement.entities.UserEntity;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupToGroupEntity implements Converter<Group, GroupEntity> {

    public GroupEntity convert(Group group) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setId(group.getId());
        groupEntity.setName(group.getName());
        if (group.getUserList() != null) {
            List<UserEntity> userEntities = new ArrayList<UserEntity>();
            UserToUserEntity userConverter = new UserToUserEntity();
            for (User u : group.getUserList()) {
                userEntities.add(userConverter.convert(u));
            }
            groupEntity.setUserList(userEntities);
        }
        return groupEntity;
    }
}
