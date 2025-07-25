package com.blog.BloggingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloggingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingAppApplication.class, args);
	}

}


// This project aims to deepen understanding how relationships are constructed in springboot.
// We will design and implement relationships, authentication, authorization etc.

// PHASE 1
// 1. Define models, repos, services and controllers for journal and users.
// 2. Blog will hold a reference to their authors (i.e, the users).
// 3. Data will be stored in a local mongodb instance.
// 4. User -> id, username (unique), password (stored in plain text temporarily)
// 5. blog -> id, title, content , authorId


// PHASE 2 
// TO BE DECIDED