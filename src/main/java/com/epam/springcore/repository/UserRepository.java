package com.epam.springcore.repository;

import com.epam.springcore.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    List<User> findAll();
    User getUserById(Long id);
    User getUserByUsername(String username);
    void saveUser(User user);
    void deleteUser(Long id);
    boolean usernameExists(String username);

}
