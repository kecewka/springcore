package com.epam.springcore.service;

import com.epam.springcore.entity.User;

import java.util.List;

public interface UserService {
    // Calls repository to get List of All Users
    List<User> getAllUsers();

    // Calls repository to get User by id
    User getUserById(Long id);

    // Calls repository to get User by username
    User getUserByUsername(String username);

    // Calls repository to check if username exists
    boolean usernameExists(String username);

    // Calls repository to create User
    void createUser(User user);

    // Calls repository to update User
    void updateUser(User user);

    // Calls repository to delete User by id
    void deleteUser(Long id);
}
