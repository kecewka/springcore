package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.User;
import com.epam.springcore.exception.UserNotFoundException;
import com.epam.springcore.repository.UserRepository;
import com.epam.springcore.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> getAllUsers() {
        LOGGER.info("Getting all users");
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        LOGGER.info("Finding user with ID: {}", id);

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
    }

    @Transactional
    @Override
    public User findUserByUsername(String username) {
        LOGGER.info("Finding user with username: {}", username);

        return userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));
    }

    @Override
    public void createUser(User user) {
        LOGGER.info("Creating user: {}", user);
        String username = generateUsername(user.getFirstName(), user.getLastName());
        String password = generatePassword();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        LOGGER.info("Updating user: {}", user);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        LOGGER.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public boolean usernameExists(String username) {
        LOGGER.info("Checking the existence of username: {}", username);
        return userRepository.existsByUsername(username);
    }

    public String generateUsername(String firstName, String lastName) {
        LOGGER.info("Generating username");
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
        LOGGER.info("Username has been generated");
        return generatedUsername;
    }

    public String generatePassword() {
        LOGGER.info("Generating password");
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567980";
        StringBuilder builder = new StringBuilder(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(chars.length());
            char randomChar = chars.charAt(randomIndex);
            builder.append(randomChar);
        }
        LOGGER.info("Password has been generated");
        return builder.toString();
    }
}
