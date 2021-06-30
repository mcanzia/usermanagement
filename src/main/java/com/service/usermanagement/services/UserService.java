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
@Transactional
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private UserEntityToUser userEntityToUser;
    @Autowired
    private UserToUserEntity userToUserEntity;

    public List<User> listAll() {
        List<UserEntity> userEntities = new ArrayList<UserEntity>();
        repo.findAll().iterator().forEachRemaining(userEntities::add);
        List<User> users = new ArrayList<User>();
        for(UserEntity userEntity : userEntities){
            User user = userEntityToUser.convert(userEntity);
            users.add(user);
        }

        return users;
    }

    public User saveOrUpdate(User user) throws DuplicateUserException {
        try {
            UserEntity userEntity = userToUserEntity.convert(user);
            return userEntityToUser.convert(repo.save(userEntity));
        }catch (DataIntegrityViolationException e){
            throw new DuplicateUserException("User name already exists.");
        }
    }

    public User get(Integer id) {
        UserEntity userEntity = repo.findById(id).get();
        if (userEntity == null) {
            throw new UserNotFoundException("User not found.");
        }
        return userEntityToUser.convert(userEntity);

    }

    public User getByEmail(String email) {
        UserEntity userEntity = repo.findByEmail(email);
        if (userEntity == null) {
            throw new UserNotFoundException("User not found.");
        }
        System.out.println("get by email: " + userEntity);
        return userEntityToUser.convert(userEntity);

    }

    public void delete(Integer id) {
        try {
            repo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException("User was not found.");
        }
    }
}

