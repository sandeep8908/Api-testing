package com.example.test.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Test {
    private String username;
    private String password;

    public static void main(String[] args) {
        Test test = new Test();
        test.setUsername("Sandeep002");
        test.setPassword("983947");

        System.out.println(test.getUsername()+ " "+ test.getPassword());
    }
}


