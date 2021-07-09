package com.service.usermanagement.services;

import com.service.usermanagement.entities.GroupEntity;
import com.service.usermanagement.exceptions.DuplicateGroupException;
import com.service.usermanagement.exceptions.DuplicateUserException;
import com.service.usermanagement.exceptions.GroupNotFoundException;
import com.service.usermanagement.exceptions.UserNotFoundException;
import com.service.usermanagement.mappings.GroupEntityToGroup;
import com.service.usermanagement.mappings.GroupToGroupEntity;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.models.User;
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

    public List<Group> listAll() {
        return groupRepo.findAll();
    }

    public Group getById(Long id) {
        Group group = groupRepo.findById(id);
        if (group == null) {
            throw new GroupNotFoundException("Group not found.");
        }
        return group;
    }

    public void insert(Group group) throws DuplicateGroupException {
        try {
            groupRepo.insert(group);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateGroupException("Group name already exists.");
        }
    }

    public void update(Group group) throws DuplicateGroupException {
        try {
            groupRepo.update(group);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateGroupException("Group name already exists.");
        }
    }

    public void delete(Long id) {
        try {
            groupRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new GroupNotFoundException("Group was not found.");
        }
    }
}
