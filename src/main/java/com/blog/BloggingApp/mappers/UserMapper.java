package com.blog.BloggingApp.mappers;

import org.springframework.stereotype.Component;

import com.blog.BloggingApp.DTO.UserDetailsForDisplay;
import com.blog.BloggingApp.models.User;

@Component
public class UserMapper {
    public UserDetailsForDisplay userToUserDetailsForDisplay(User user) {
        if (user == null) return null;
        UserDetailsForDisplay userDetails = new UserDetailsForDisplay(user.getId(), user.getUsername(), user.getRoles());
        return userDetails;
    }
}
