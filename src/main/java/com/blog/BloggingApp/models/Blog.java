package com.blog.BloggingApp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "blog")
@NoArgsConstructor
@Data
public class Blog {

    @Id
    private String id;
    
    private String title;

    private String content;

    private String userId;

}
