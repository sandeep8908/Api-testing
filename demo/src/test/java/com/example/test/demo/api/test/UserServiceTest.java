package com.example.test.demo.api.test;

import com.example.test.demo.entity.User;
import com.example.test.demo.repository.UserRepository;
import com.example.test.demo.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testAddUser() {
        User user = getUser(7398);

        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.addUser(user);

        verify(userRepository, times(1)).save(savedUser);
        assertEquals(user, savedUser);
    }

    @Test
    void testGetUserById() {
        User user = getUser(12);

        lenient().when(userRepository.findById(12)).thenReturn(Optional.of(user));
        User userFromDb = userService.getUser(12);

        assertEquals(user.getUserId(), userFromDb.getUserId());

        verify(userRepository, times(1)).findById(12);
    }

    @Test
    void testGetUserById_WithNonExistentUserId() {
        int nonExistentUserId = 67;

        when(userRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());

        User result = userService.getUser(nonExistentUserId);
        assertNull(result);
        verify(userRepository, times(1)).findById(nonExistentUserId);
    }

    @Test
    void testUpdateUser() {
        User user = getUser(12);

        User updateUser = new User();
        updateUser.setUserId(12);
        updateUser.setUsername("satyamp008");
        updateUser.setEmail("satyam897@gmail.com");
        updateUser.setPassword("satyam@0008");
        updateUser.setStatus("inactive");

        lenient().when(userRepository.findById(12)).thenReturn(Optional.of(user));
        lenient().when(userRepository.save(updateUser)).thenReturn(updateUser);
        User resultUser = userService.updateUser(user.getUserId(), updateUser);

        assertEquals("inactive", resultUser.getStatus());

        verify(userRepository, times(1)).findById(user.getUserId());
        verify(userRepository, times(1)).save(any(User.class));
    }
    @Test
    void testUpdateUser_WithNonExistentUserId(){
        int nonExistentUserId = 27;
        User updateUser = new User();
        updateUser.setUserId(12);
        updateUser.setUsername("satyamp008");
        updateUser.setEmail("satyam897@gmail.com");
        updateUser.setPassword("satyam@0008");
        updateUser.setStatus("inactive");

        when(userRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());
        User resultUser
                = userService.updateUser(nonExistentUserId, updateUser);
        assertNull(resultUser);

        verify(userRepository,times(1)).findById(nonExistentUserId);
    }

    @Test
    void testDeleteUser() {
        User user = getUser(23);

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.ofNullable(user));

        String result = userService.deleteUser(user.getUserId());
        assertEquals("User deleted Successfully", result);
        verify(userRepository, times(1)).deleteById(user.getUserId());
    }
    @Test
    void testDeleteUser_WithNonExistentUserId(){
        int nonExistentUserId = 64;

        when(userRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());

        String result = userService.deleteUser(nonExistentUserId);
        assertEquals("User not deleted !",result);

        verify(userRepository,times(1)).findById(nonExistentUserId);
    }

    @Test
    void testFindAllUser() {
        User user1 = getUser(32);
        User user2 = getUser(32);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);
        List<User> allUsers = userService.getAllUsers();

        assertEquals(users.size(), allUsers.size());
    }

    @NotNull
    private static User getUser(int userId) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername("satyamp008");
        user.setEmail("satyam897@gmail.com");
        user.setPassword("satyam@0008");
        user.setStatus("active");
        return user;
    }
}
