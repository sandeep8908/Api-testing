package com.example.test.demo.service;

import com.example.test.demo.entity.User;
import com.example.test.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        User userFromDb = userRepository.save(user);
        return userFromDb;
    }

    public User getUser(int userId) {
        Optional<User> user = userRepository.findById(userId);
        User userFromDb = user.get();
        return userFromDb;
    }

    public User updateUser(int userId, User user) {
        User userFromDb = userRepository.findById(userId).get();
        userFromDb.setUsername(user.getUsername());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setStatus(user.getStatus());

        return userRepository.save(userFromDb);
    }

    public String deleteUser(int userId) {
        User user = userRepository.findById(userId).get();
        userRepository.deleteById(userId);
        return "User deleted Successfully";
    }

    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

}
