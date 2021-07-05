package com.service.usermanagement.mappings;

import com.service.usermanagement.entities.GroupEntity;
import com.service.usermanagement.models.Group;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GroupToGroupEntity implements Converter<Group, GroupEntity> {

    public GroupEntity convert(Group group) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setId(group.getId());
        groupEntity.setName(group.getName());
        return groupEntity;
    }
}
