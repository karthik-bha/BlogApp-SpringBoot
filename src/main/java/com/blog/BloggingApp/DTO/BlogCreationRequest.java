package com.blog.BloggingApp.DTO;

import com.blog.BloggingApp.models.Blog;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogCreationRequest {
    private Blog blog;
    private String userId;
}