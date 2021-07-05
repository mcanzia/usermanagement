package com.service.usermanagement.mappings;

import com.service.usermanagement.entities.GroupEntity;
import com.service.usermanagement.entities.UserEntity;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GroupEntityToGroup implements Converter<GroupEntity, Group> {

    public Group convert(GroupEntity groupEntity) {
        Group newGroup = new Group();
        newGroup.setId(groupEntity.getId());
        newGroup.setName(groupEntity.getName());
        return newGroup;
    }
}
