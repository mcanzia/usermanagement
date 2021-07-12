package com.service.usermanagement.controllers;

import com.service.usermanagement.exceptions.DuplicateUserException;
import com.service.usermanagement.exceptions.UserNotFoundException;
import com.service.usermanagement.models.User;
import com.service.usermanagement.services.UserService;
import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Service controller to handle CRUD operations with respect to User object
 * @author Michael Canziani
 */
@RestController
public class UserController {

    /** Used to perform user configuration between Controller and Repository classes*/
    private UserService userService;
    /** Used to encrypt passwords for users */
    private PasswordEncoder encoder;

    /**
     * Constructor for UserController, defines UserService and PasswordEncoder injections
     * @param userService dependency to be injected by UserService
     * @param encoder dependency to be injected by PasswordEncoder
     */
    public UserController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    /**
     * Retrieve all User records from database
     * @return list of User records from database
     */
    @GetMapping("/users")
    public List<User> getAll() {
        return userService.listAll();
    }

    /**
     * Retrieve all users not currently assigned to a group from database
     * @return list of unassigned users from database
     */
    @GetMapping("/users/unassigned")
    public List<User> listUnassigned() {
        List<User> userList = new ArrayList<User>();
        userList = userService.listAll();
        userList.removeIf(user -> user.getGroupId() != null);
        System.out.println(userList);
        return userList;
    }

    /**
     * Retrieve specific User from database based on id
     * @param id of user to return
     * @return single user from database and success status of callout
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        try {
            User user = userService.get(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create new User record in database
     * @param user details of user to be created
     * @return success status of callout
     * @throws DuplicateUserException if user with identical email already exists in database
     */
    @PostMapping("/users")
    public ResponseEntity<User> add(@RequestBody User user) throws DuplicateUserException {
        try {
            user.setPassword(encoder.encode(Math.random() + ""));
            userService.insert(user);
            return new ResponseEntity<User>(HttpStatus.CREATED);
        } catch (DuplicateUserException e) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Update User record in database
     * Used most often for adding/removing user records from groups
     * @param id selected user to update
     * @param user new details to apply to user record
     * @return success status of callout
     * @throws BadHttpRequest if error occurs during HTTP handling
     * @throws DuplicateUserException if user email is updated to match existing user's in database
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) throws BadHttpRequest, DuplicateUserException {
        try {
            User updateUser = userService.get(id);
            if (updateUser != null) {
                updateUser.setFirstName(user.getFirstName());
                updateUser.setLastName(user.getLastName());
                updateUser.setEmail(user.getEmail());
                updateUser.setPassword(user.getPassword());
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

    /**
     * Delete user record in database based on id
     * @param id id of selected user to be deleted
     * @return success status of callout
     */
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
