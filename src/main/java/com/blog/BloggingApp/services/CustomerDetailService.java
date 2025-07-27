package com.blog.BloggingApp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.BloggingApp.models.User;
import com.blog.BloggingApp.repositories.UserRepo;

@Service
public class CustomerDetailService implements UserDetailsService{
    private final UserRepo userRepo;

    public CustomerDetailService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException{
        User user = userRepo.findUserByUsername(Username);
        if(user == null) throw new UsernameNotFoundException("User not found!");

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER","ADMIN")
                .build();

    }
    
}
