package com.blog.BloggingApp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "user")
@NoArgsConstructor
public class User {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String userName;

    private String password;
    
}
