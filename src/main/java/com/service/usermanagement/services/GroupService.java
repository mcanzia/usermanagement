package com.service.usermanagement.services;

import com.service.usermanagement.entities.GroupEntity;
import com.service.usermanagement.exceptions.DuplicateGroupException;
import com.service.usermanagement.exceptions.GroupNotFoundException;
import com.service.usermanagement.mappings.GroupEntityToGroup;
import com.service.usermanagement.mappings.GroupToGroupEntity;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupService {
    @Autowired
    private GroupRepository groupRepo;
    @Autowired
    private GroupEntityToGroup groupEntityToGroup;
    @Autowired
    private GroupToGroupEntity groupToGroupEntity;

    public List<Group> listAll() {
        List<GroupEntity> groupEntities = new ArrayList<GroupEntity>();
        groupRepo.findAll().iterator().forEachRemaining(groupEntities::add);
        List<Group> groups = new ArrayList<Group>();
        for (GroupEntity groupEntity : groupEntities) {
            Group group = groupEntityToGroup.convert(groupEntity);
            groups.add(group);
        }

        return groups;
    }

    public Group saveOrUpdate(Group group) throws DuplicateGroupException {
        try {
            GroupEntity groupEntity = groupToGroupEntity.convert(group);
            return groupEntityToGroup.convert(groupRepo.save(groupEntity));
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateGroupException("Group name already exists.");
        }
    }

    public Group get(Long id) {
        GroupEntity groupEntity = groupRepo.findById(id).get();
        if (groupEntity == null) {
            throw new GroupNotFoundException("Group not found.");
        }
        return groupEntityToGroup.convert(groupEntity);

    }

    public void delete(Long id) {
        try {
            groupRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new GroupNotFoundException("Group was not found.");
        }
    }
}
