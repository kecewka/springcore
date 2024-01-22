package com.epam.springcore.repository;

import com.epam.springcore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Calls database to get a list of all Records in users table
    List<User> findAll();

    // Calls database to get a single Record in users table which matches id
    <Optional> User findById(Long id);

    // Calls database to get a single Record in users table which matches username
    User getUserByUsername(String username);

    // Calls database to check the number of users who have particular username
    //boolean usernameExists(String username);

}
