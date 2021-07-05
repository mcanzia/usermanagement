package com.service.usermanagement.controllers;

import com.service.usermanagement.exceptions.DuplicateGroupException;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.services.GroupService;
import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    public List<Group> list() {
        return groupService.listAll();
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<Group> get(@PathVariable Long id) {
        try {
            Group group = groupService.get(id);
            return new ResponseEntity<Group>(group, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
        }
    }

    // RESTful API method for Create operation
    @PostMapping("/groups")
    public void add(@RequestBody Group group) throws DuplicateGroupException {
        groupService.saveOrUpdate(group);
    }

    // RESTful API method for Update operation
    @PutMapping("/groups/{id}")
    public ResponseEntity<Group> update(@PathVariable Long id, @RequestBody Group group) throws BadHttpRequest, DuplicateGroupException {
        try {
            Group updateGroup = groupService.get(id);
            updateGroup.setName(group.getName());
            final Group responseGroup = groupService.saveOrUpdate(updateGroup);
            return new ResponseEntity<Group>(responseGroup, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
        }

    }

    // RESTful API method for Delete operation
    @DeleteMapping("/groups/{id}")
    public String delete(@PathVariable Long id) {
        groupService.delete(id);
        return "Deleted Group Id - " + id;
    }

}
