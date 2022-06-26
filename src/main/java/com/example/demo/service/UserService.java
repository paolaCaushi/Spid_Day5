package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    //search user by Id
    public Optional<User> getUserById(long id) {

        return userRepo.findById(id);
    }

    //create user
    public User createUser(User user) {

        return userRepo.save(user);
    }

    //update user by inserting their id
    public User updateUser(User user, long id) throws Exception {
        if (getUserById(id).isEmpty()) {
            throw new Exception("You are trying to update an user that does not exist");
        }
        user.setId(id);
        return userRepo.save(user);
    }

}