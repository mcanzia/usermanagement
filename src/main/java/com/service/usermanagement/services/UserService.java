package com.service.usermanagement.services;

import com.service.usermanagement.exceptions.DuplicateUserException;
import com.service.usermanagement.exceptions.UserNotFoundException;
import com.service.usermanagement.models.User;
import com.service.usermanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for User controller, acts as middleman between Controller and Repository/DAO classes
 * @author Michael Canziani
 */
@Service
public class UserService {

    /** User DAO repository which interacts directly with database*/
    private UserRepository repo;

    /**
     * Constructor for UserService, defines dependencies for UserRepository to be injected
     * @param repo
     */
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    /**
     * Receives request from list() method in UserController
     * and sends request to repo to interact with database
     * @return response from repo with list of User records
     */
    public List<User> listAll() {
        return repo.findAll();
    }

    /**
     * Receives request from get() method in User controller
     * and sends request to repo to interact with database
     * @param id user id to retrieve from database
     * @return response from repo with requested User record
     */
    public User get(Long id) {
        User user = repo.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }
        return user;
    }

    /**
     * Receives request from add() method in User controller
     * and sends request to repo to interact with database
     * @param user user record to add to database
     */
    public void insert(User user) throws DuplicateUserException {
        try {
            repo.insert(user);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateUserException("User name already exists.");
        }
    }

    /**
     * Receives request from update() method in User controller
     * and sends request to repo to interact with database
     * @param user user record to update in database
     */
    public void update(User user) throws DuplicateUserException {
        try {
            repo.update(user);
        }catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            throw new DuplicateUserException("User name already exists.");
        }
    }

    /**
     * Receives request from register() method in User controller
     * and sends request to repo to interact with database
     * @param user user record to update in database
     */
    public void registerUser(User user) {
        try {
            repo.registerUser(user);
        }catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Registration failed");
        }
    }

    /**
     * Receives request from delete() method in User controller
     * and sends request to repo to interact with database
     * @param id user record to remove from database
     */
    public void delete(Long id) {
        try {
            repo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException("User was not found.");
        }
    }

    /**
     * Receives request from getUserByUsername() method in SecurityDetails controller
     * and sends request to repo to interact with database
     * @param email email of user to retrieve
     * @return User record from database
     */
    public User getByUsername(String email) {
        User user = repo.findByUsername(email);
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }
        return user;
    }
}

