package com.epam.springcore.repository;

import com.epam.springcore.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository {
    // Calls database to get a list of all Records in users table
    List<User> findAll();

    // Calls database to get a single Record in users table which matches id
    User findById(Long id);

    // Calls database to get a single Record in users table which matches username
    User getUserByUsername(String username);

    // Calls database to create a record in users table
    boolean saveUser(User user);

    // Calls database to update a record in users table
    void updateUser(User user);

    // Calls database to delete a single Record in trainees table which matches id
    void deleteUser(Long id);

    // Calls database to check the number of users who have particular username
    boolean usernameExists(String username);

}
