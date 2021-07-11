package com.service.usermanagement.controllers;

import com.service.usermanagement.exceptions.DuplicateGroupException;
import com.service.usermanagement.exceptions.DuplicateUserException;
import com.service.usermanagement.exceptions.GroupNotFoundException;
import com.service.usermanagement.exceptions.UserNotFoundException;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.models.User;
import com.service.usermanagement.services.GroupService;
import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service controller to handle CRUD operations with respect to Group object
 * @author Michael Canziani
 */
@RestController
public class GroupController {

    /** Used to perform group configuration between Controller and Repository classes*/
    @Autowired
    private GroupService groupService;

    /**
     * Retrieves list of all user_groups from database
     * @return all user_groups
     */
    @GetMapping("/groups")
    public List<Group> list() {
        return groupService.listAll();
    }

    /**
     * Retrieves single Group from database based on id
     * @param id id of selected group
     * @return single group matching provided id
     */
    @GetMapping("/groups/{id}")
    public ResponseEntity<Group> get(@PathVariable Long id) {
        try {
            Group group = groupService.getById(id);
            return new ResponseEntity<Group>(group, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Adds new Group record to database
     * @param group group record to add
     * @return response status of callout
     * @throws DuplicateGroupException if existing group with duplicate name exists
     */
    @PostMapping("/groups")
    public ResponseEntity<Group> add(@RequestBody Group group) throws DuplicateGroupException {
        try {
            groupService.insert(group);
            return new ResponseEntity<Group>(HttpStatus.CREATED);
        } catch (DuplicateGroupException e) {
            return new ResponseEntity<Group>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Updates Group in database with given id based on new values provided
     * @param id select group in db
     * @param group updates to select group values
     * @return response status of callout
     * @throws BadHttpRequest if error occurs with HTTP callout
     * @throws DuplicateGroupException if group name is updated to match an already existing group record
     */
    @PutMapping("/groups/{id}")
    public ResponseEntity<Group> update(@PathVariable Long id, @RequestBody Group group) throws BadHttpRequest, DuplicateGroupException {
        try {
            Group updateGroup = groupService.getById(id);
            updateGroup.setName(group.getName());
            groupService.update(updateGroup);
            return new ResponseEntity<Group>(HttpStatus.OK);
        } catch (GroupNotFoundException e) {
            return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete group record from db based on id
     * @param id id of group to be deleted
     * @return response status of callout
     */
    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Group> delete(@PathVariable Long id) {
        try {
            groupService.delete(id);
            return new ResponseEntity<Group>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
        }

    }

}
