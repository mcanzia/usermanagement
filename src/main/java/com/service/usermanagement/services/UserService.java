package com.service.usermanagement.services;

import com.service.usermanagement.entities.UserEntity;
import com.service.usermanagement.exceptions.DuplicateUserException;
import com.service.usermanagement.exceptions.UserNotFoundException;
import com.service.usermanagement.mappings.UserEntityToUser;
import com.service.usermanagement.mappings.UserToUserEntity;
import com.service.usermanagement.models.User;
import com.service.usermanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
            //throw new DuplicateUserException("User name already exists.");
            System.out.println(e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            repo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException("User was not found.");
        }
    }
}

