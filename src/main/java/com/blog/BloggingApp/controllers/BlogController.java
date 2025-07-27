package com.blog.BloggingApp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.BloggingApp.models.Blog;
import com.blog.BloggingApp.services.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return new ResponseEntity<>(blogService.getAllBlogs(), HttpStatus.OK);
    }

    @GetMapping("/user-blogs")
    public ResponseEntity<List<Blog>> getUserBlogs() {
        return new ResponseEntity<>(blogService.getUserBlogs(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> createNewBlog(@RequestBody Blog newBlog) {
        return new ResponseEntity<>(blogService.createNewBlog(newBlog), HttpStatus.CREATED);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Blog>> searchBlogByTitle(@PathVariable String title) {
        return new ResponseEntity<>(blogService.findBlogByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Blog> searchBlogById(@PathVariable String id) {
        return new ResponseEntity<>(blogService.findBlogById(id), HttpStatus.OK);
    }

    // Edit a blog (only if it belongs to the logged-in user)
    @PutMapping("/user-blogs/{id}")
    public ResponseEntity<Blog> editBlog(@PathVariable String id, @RequestBody Blog updatedBlog) {
        Blog blog = blogService.editBlog(id, updatedBlog);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    // Delete a blog (only if it belongs to the logged-in user)
    @DeleteMapping("/user-blogs/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable String id) {
        blogService.deleteUserBlog(id); 
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
