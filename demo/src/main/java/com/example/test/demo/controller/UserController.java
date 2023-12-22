package com.example.test.demo.controller;

import com.example.test.demo.entity.User;
import com.example.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") int userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable("userId") int userId) {
        User updateUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId){
        String message = userService.deleteUser(userId);
        return ResponseEntity.ok(message);
    }
}
