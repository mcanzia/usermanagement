package com.service.usermanagement.controllers;

import com.service.usermanagement.exceptions.DuplicateUserException;
import com.service.usermanagement.models.User;
import com.service.usermanagement.services.UserService;
import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> list() {
        return service.listAll();
    }

    @GetMapping("/users/unassigned")
    public List<User> listUnassigned() {
        List<User> userList = new ArrayList<User>();
        userList = service.listAll();
        userList.removeIf(user -> user.getGroupId() != null);
        System.out.println(userList);
        return userList;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        try {
            User user = service.get(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public void add(@RequestBody User user) throws DuplicateUserException {
        service.saveOrUpdate(user);
    }

    // RESTful API method for Update operation
    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) throws BadHttpRequest, DuplicateUserException {
        try {
            User updateUser = service.get(id);
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
            updateUser.setEmail(user.getEmail());
            updateUser.setRole(user.getRole());
            updateUser.setGroupId(user.getGroupId());
            final User responseUser = service.saveOrUpdate(updateUser);
            return new ResponseEntity<User>(responseUser, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

    }

    // RESTful API method for Delete operation
    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted User Id - " + id;
    }

    @GetMapping("/users/email")
    public User findByEmail(@RequestParam(value = "email")String email) {
        return service.getByEmail(email);
    }

    @GetMapping("/users/group/{groupId}")
    public List<User> findByGroup(@RequestParam(value = "groupId")Long groupId) {
        System.out.println("here in controller");
        return service.getByGroup(groupId);
    }
}
