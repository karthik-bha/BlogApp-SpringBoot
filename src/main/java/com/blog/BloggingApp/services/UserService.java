package com.blog.BloggingApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.BloggingApp.models.User;
import com.blog.BloggingApp.repositories.UserRepo;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserByUserName(String userName) {
        User user = userRepo.findUserByUserName(userName);
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        return user;
    }

    public User getUserById(String userId) {
       return userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found!"));
    }


    public User createNewUser(User user) {
        // check if username is taken
        if (userRepo.existsByUserName(user.getUserName())) {
            throw new RuntimeException("Username already taken: " + user.getUserName());
        }
        try {
            return userRepo.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving user", e);
        }
    }
}
