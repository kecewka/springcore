package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.User;
import com.epam.springcore.repository.UserRepository;
import com.epam.springcore.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        logger.info("Getting all users");
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        logger.info("Finding user with ID: {}", id);
        return userRepository.findById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        logger.info("Finding user with username: {}", username);
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void createUser(User user) {
        logger.info("Creating user: {}", user);
        String username = generateUsername(user.getFirstName(), user.getLastName());
        String password = generatePassword();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        logger.info("Updating user: {}", user);
        userRepository.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        logger.info("Deleting user with ID: {}", id);
        userRepository.deleteUser(id);
    }

    @Override
    public boolean usernameExists(String username) {
        logger.info("Checking the existence of username: {}", username);
        return userRepository.usernameExists(username);
    }

    public String generateUsername(String firstName, String lastName) {
        logger.info("Generating username");
        String baseUsername = firstName.toLowerCase() + "." + lastName.toLowerCase();
        String generatedUsername = baseUsername;

        if (usernameExists(generatedUsername)) {
            int index = 1;
            while (true) {
                generatedUsername = baseUsername + index;
                if (!usernameExists(generatedUsername)) {
                    return generatedUsername;
                }
                index++;
            }
        }
        logger.info("Username has been generated");
        return generatedUsername;
    }

    public String generatePassword() {
        logger.info("Generating password");
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567980";
        StringBuilder builder = new StringBuilder(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(chars.length());
            char randomChar = chars.charAt(randomIndex);
            builder.append(randomChar);
        }
        logger.info("Password has been generated");
        return builder.toString();
    }
}
