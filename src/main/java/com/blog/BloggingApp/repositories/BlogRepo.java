package com.blog.BloggingApp.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blog.BloggingApp.models.Blog;

@Repository
public interface BlogRepo extends MongoRepository<Blog, String> {
    List<Blog> findBlogByTitle(String title);
}
