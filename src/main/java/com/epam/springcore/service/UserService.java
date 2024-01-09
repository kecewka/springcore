package com.epam.springcore.service;

import com.epam.springcore.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
    boolean usernameExists(String username);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
