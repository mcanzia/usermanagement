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

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> listAll() {
        return repo.findAll();
    }

    public User get(Long id) {
        User user = repo.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }
        return user;
    }

    public void insert(User user) throws DuplicateUserException {
        try {
            repo.insert(user);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateUserException("User name already exists.");
        }
    }

    public void update(User user) throws DuplicateUserException {
        try {
            repo.update(user);
        }catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            throw new DuplicateUserException("User name already exists.");
        }
    }

    public void registerUser(User user) {
        try {
            repo.registerUser(user);
        }catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Registration failed");
        }
    }

    public void delete(Long id) {
        try {
            repo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException("User was not found.");
        }
    }

    public User getByUsername(String email) {
        User user = repo.findByUsername(email);
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }
        return user;
    }
}

