package com.service.usermanagement.services;

import com.service.usermanagement.exceptions.DuplicateGroupException;
import com.service.usermanagement.exceptions.GroupNotFoundException;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for Group controller, acts as middleman between Controller and Repository/DAO classes
 * @author Michael Canziani
 */
@Service
public class GroupService {
    /** Group DAO repository which interacts directly with database*/
    @Autowired
    private GroupRepository groupRepo;

    /**
     * Receives request from list() method in GroupController
     * and sends request to repo to interact with database
     * @return response from repo with list of Group records
     */
    public List<Group> listAll() {
        return groupRepo.findAll();
    }

    /**
     * Receives request from get() method in Group controller
     * and sends request to repo to interact with database
     * @param id group id to retrieve from database
     * @return response from repo with requested Group record
     */
    public Group getById(Long id) {
        Group group = groupRepo.findById(id);
        if (group == null) {
            throw new GroupNotFoundException("Group not found.");
        }
        return group;
    }

    /**
     * Receives request from add() method in Group controller
     * and sends request to repo to interact with database
     * @param group group record to add to database
     */
    public void insert(Group group) throws DuplicateGroupException {
        try {
            groupRepo.insert(group);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateGroupException("Group name already exists.");
        }
    }

    /**
     * Receives request from update() method in Group controller
     * and sends request to repo to interact with database
     * @param group group record to update in database
     */
    public void update(Group group) throws DuplicateGroupException {
        try {
            groupRepo.update(group);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateGroupException("Group name already exists.");
        }
    }

    /**
     * Receives request from delete() method in Group controller
     * and sends request to repo to interact with database
     * @param id group record to remove from database
     */
    public void delete(Long id) {
        try {
            groupRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new GroupNotFoundException("Group was not found.");
        }
    }
}
