package com.service.usermanagement.controllers;

import com.service.usermanagement.exceptions.DuplicateUserException;
import com.service.usermanagement.exceptions.UserNotFoundException;
import com.service.usermanagement.mappings.UserMapper;
import com.service.usermanagement.models.User;
import com.service.usermanagement.repositories.UserRepository;
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
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAll() {
        return userService.listAll();
    }

    @GetMapping("/users/unassigned")
    public List<User> listUnassigned() {
        List<User> userList = new ArrayList<User>();
        userList = userService.listAll();
        userList.removeIf(user -> user.getGroupId() != null);
        System.out.println(userList);
        return userList;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        try {
            User user = userService.get(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> add(@RequestBody User user) throws DuplicateUserException {
        try {
            userService.insert(user);
            return new ResponseEntity<User>(HttpStatus.CREATED);
        } catch (DuplicateUserException e) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) throws BadHttpRequest, DuplicateUserException {
        try {
            User updateUser = userService.get(id);
            if (updateUser != null) {
                updateUser.setFirstName(user.getFirstName());
                updateUser.setLastName(user.getLastName());
                updateUser.setEmail(user.getEmail());
                updateUser.setRole(user.getRole());
                updateUser.setGroupId(user.getGroupId());
                userService.update(updateUser);
                return new ResponseEntity<User>(HttpStatus.OK);
            }
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            return new ResponseEntity<User>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

    }

}
