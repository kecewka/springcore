package com.epam.springcore.service.impl;

import com.epam.springcore.entity.User;
import com.epam.springcore.exception.UserNotFoundException;
import com.epam.springcore.repository.UserRepository;
import com.epam.springcore.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
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
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Getting all users", transactionId);
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Finding user with ID: {}", transactionId, id);

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
    }

    @Transactional
    @Override
    public User findUserByUsername(String username) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Finding user with username: {}", transactionId, username);

        return userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));
    }

    @Override
    public void createUser(User user) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Creating user: {}", transactionId, user);
        String username = generateUsername(user.getFirstName(), user.getLastName());
        String password = generatePassword();

        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Updating user: {}", transactionId, user);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Deleting user with ID: {}", transactionId, id);
        userRepository.deleteById(id);
    }

    @Override
    public boolean usernameExists(String username) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Checking the existence of username: {}", transactionId, username);
        return userRepository.existsByUsername(username);
    }

    public String generateUsername(String firstName, String lastName) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Generating username", transactionId);
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
        LOGGER.info("[Transaction ID: {}] Username has been generated", transactionId);
        return generatedUsername;
    }

    public String generatePassword() {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Generating password", transactionId);
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567980";
        StringBuilder builder = new StringBuilder(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(chars.length());
            char randomChar = chars.charAt(randomIndex);
            builder.append(randomChar);
        }
        LOGGER.info("[Transaction ID: {}] Password has been generated", transactionId);
        return builder.toString();
    }
}
