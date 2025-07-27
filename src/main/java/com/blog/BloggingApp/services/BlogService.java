package com.blog.BloggingApp.services;

import java.util.List;

import com.blog.BloggingApp.repositories.UserRepo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.blog.BloggingApp.models.Blog;
import com.blog.BloggingApp.models.User;
import com.blog.BloggingApp.repositories.BlogRepo;

@Service
public class BlogService {

    private final UserRepo userRepo;

    private BlogRepo blogRepo;

    public BlogService(BlogRepo blogRepo, UserService userService, UserRepo userRepo) {
        this.blogRepo = blogRepo;
        this.userRepo = userRepo;

    }

    public Blog createNewBlog(Blog blog) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // from Basic Auth header

        User user = userRepo.findUserByUsername(username);
        // System.out.println(user.getId());
        if (user == null) {
            throw new RuntimeException("Authenticated user not found in DB");
        }

        if (blog.getTitle().trim().equals("") || blog.getContent().trim().equals("")) {
            throw new IllegalArgumentException("Title and content must not be empty!");
        }

        blog.setUserId(user.getId()); // attach logged-in user ID automatically
        return blogRepo.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    public List<Blog> getUserBlogs() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // from Basic Auth header
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("Authenticated user not found in DB");
        }
        return blogRepo.findAllByUserId(user.getId());
    }

    public Blog findBlogById(String id) {
        return blogRepo.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public List<Blog> findBlogByTitle(String title) {
        return blogRepo.findBlogByTitle(title);
    }

    public void deleteAllBlogsByUserId(String userId) {
        blogRepo.deleteAllByUserId(userId);
        return;
    }

    public void deleteUserBlog(String blogId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findUserByUsername(username);

        if (user == null) {
            throw new RuntimeException("Authenticated user not found in DB");
        }

        Blog blog = blogRepo.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        if (!user.getId().equals(blog.getUserId())) {
            throw new RuntimeException("You cannot delete someone else's blog");
        }

        blogRepo.delete(blog);
    }

    public Blog editBlog(String blogId, Blog updatedBlog) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findUserByUsername(username);

        if (user == null) {
            throw new RuntimeException("Authenticated user not found in DB");
        }

        Blog blog = blogRepo.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        if (!user.getId().equals(blog.getUserId())) {
            throw new RuntimeException("You cannot edit someone else's blog");
        }

        if(updatedBlog.getTitle() !=null) blog.setTitle(updatedBlog.getTitle());
        if(updatedBlog.getContent() !=null) blog.setContent(updatedBlog.getContent());
        return blogRepo.save(blog);
    }
}
