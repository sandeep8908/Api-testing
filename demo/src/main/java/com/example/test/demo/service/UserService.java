package com.example.test.demo.service;

import com.example.test.demo.entity.User;
import com.example.test.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public User updateUser(int userId, User user) {
        Optional<User> userFromDb = userRepository.findById(userId);
        if (userFromDb.isPresent()) {
            User exitingUser = userFromDb.get();
            exitingUser.setUsername(user.getUsername());
            exitingUser.setPassword(user.getPassword());
            exitingUser.setEmail(user.getEmail());
            exitingUser.setStatus(user.getStatus());

            return userRepository.save(exitingUser);
        }
        return null;
    }

    public String deleteUser(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return "User deleted Successfully";
        }
        return "User not deleted !";
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

}
