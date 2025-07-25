package com.blog.BloggingApp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.BloggingApp.models.User;
import com.blog.BloggingApp.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        User newUser = userService.createNewUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/userName")
    public ResponseEntity<User> getUserByUserName(@RequestParam String username ){
        return new ResponseEntity<>(userService.getUserByUserName(username), HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<User> getUserById(@RequestParam String userId ){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

}
