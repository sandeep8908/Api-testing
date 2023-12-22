package com.example.test.demo.api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String status;
}
