package com.service.usermanagement.controllers;

import com.service.usermanagement.exceptions.DuplicateUserException;
import com.service.usermanagement.models.User;
import com.service.usermanagement.services.UserService;
import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    // RESTful API methods for Retrieval operations
    //@CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/users")
    public List<User> list() {
        return service.listAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User user = service.get(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    // RESTful API method for Create operation
    @PostMapping("/users")
    public void add(@RequestBody User user) throws DuplicateUserException {
        service.saveOrUpdate(user);
    }

    // RESTful API method for Update operation
    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user) throws BadHttpRequest, DuplicateUserException {
        try {
            User updateUser = service.get(id);
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
            updateUser.setEmail(user.getEmail());
            final User responseUser = service.saveOrUpdate(updateUser);
            return new ResponseEntity<User>(responseUser, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

    }

    // RESTful API method for Delete operation
    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "Deleted User Id - " + id;
    }

    @GetMapping("/users/email")
    public User findByEmail(@RequestParam(value = "email")String email) {
        return service.getByEmail(email);
    }
}
