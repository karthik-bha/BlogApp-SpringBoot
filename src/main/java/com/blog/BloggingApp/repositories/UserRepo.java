package com.blog.BloggingApp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blog.BloggingApp.models.User;


@Repository
public interface UserRepo extends MongoRepository<User, String> {
    User findUserByUsername(String Username);
    boolean existsByUsername(String Username);
    void deleteByUsername(String Username);
}
