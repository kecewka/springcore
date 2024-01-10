package com.epam.springcore.repository;

import com.epam.springcore.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    List<User> findAll();
    User findById(Long id);
    User getUserByUsername(String username);
    boolean saveUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    boolean usernameExists(String username);

}
