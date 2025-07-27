package com.blog.BloggingApp.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.BloggingApp.DTO.UserDetailsForDisplay;
import com.blog.BloggingApp.mappers.UserMapper;
import com.blog.BloggingApp.models.User;
import com.blog.BloggingApp.repositories.BlogRepo;
import com.blog.BloggingApp.repositories.UserRepo;

@Service
public class UserService {

    private final BlogRepo blogRepo;
    private final UserMapper userMapper;

    private UserRepo userRepo;
    private final PasswordEncoder passwordEncoder; // injected

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, BlogRepo blogRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.blogRepo = blogRepo;
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserByUsername(String Username) {
        User user = userRepo.findUserByUsername(Username);
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        return user;
    }

    public User getUserById(String userId) {
        return userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public UserDetailsForDisplay createNewAdminUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Provide a Username");
        }
        // check if Username is taken
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already taken: " + user.getUsername());
        }
        try {
            user.getRoles().add("ROLE_ADMIN");
            String encryptedPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPass);
            User newAdmin = userRepo.save(user);
            return userMapper.userToUserDetailsForDisplay(newAdmin);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving user", e);
        }

    }

    public UserDetailsForDisplay createNewUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Provide a Username");
        }

        // check if Username is taken
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already taken: " + user.getUsername());
        }
        try {
            user.getRoles().add("ROLE_USER");
            String encryptedPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPass);
            User newUser = userRepo.save(user);
            return userMapper.userToUserDetailsForDisplay(newUser);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving user", e);
        }
    }

    public void deleteUserByUsername(String Username) {
        if (!userRepo.existsByUsername(Username)) {
            throw new RuntimeException("User doesnt exist");
        }
        try {
            User user = userRepo.findUserByUsername(Username);
            String userId = user.getId();

            // delete any blogs associated with this user id
            blogRepo.deleteAllByUserId(userId);

            userRepo.deleteByUsername(Username);
        } catch (Exception e) {
            throw new RuntimeException("Error during deletion!", e);
        }
        return;
    }
}
