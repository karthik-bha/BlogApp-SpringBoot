package com.blog.BloggingApp.services;

import java.util.List;


import com.blog.BloggingApp.repositories.UserRepo;
import org.springframework.stereotype.Service;

import com.blog.BloggingApp.DTO.BlogCreationRequest;
import com.blog.BloggingApp.models.Blog;
import com.blog.BloggingApp.repositories.BlogRepo;

@Service
public class BlogService {

    private final UserRepo userRepo;

    private BlogRepo blogRepo;

    public BlogService(BlogRepo blogRepo, UserService userService, UserRepo userRepo) {
        this.blogRepo = blogRepo;
        this.userRepo = userRepo;

    }

    public Blog createNewBlog(BlogCreationRequest newBlog) {
        if (!userRepo.existsById(newBlog.getUserId())) {
            throw new RuntimeException("User not found, cannot save!");
        }
        try {
            Blog blog = newBlog.getBlog(); 
            blog.setUserId(newBlog.getUserId());
            return blogRepo.save(blog);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving the blog");
        }
    }

    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    public Blog findBlogById(String id) {
        return blogRepo.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public List<Blog> findBlogByTitle(String title){
        return blogRepo.findBlogByTitle(title);
    }

}
