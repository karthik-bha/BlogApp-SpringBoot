package com.blog.BloggingApp.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsForDisplay {
    private String id;
    private String Username;
    private List<String> role;
}
