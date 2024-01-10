package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.User;
import com.epam.springcore.repository.UserRepository;
import com.epam.springcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void createUser(User user) {
        String username = generateUsername(user.getFirstName(), user.getLastName());
        String password = generatePassword();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.usernameExists(username);
    }

    public String generateUsername(String firstName, String lastName) {
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

        return generatedUsername;
    }

    public String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567980";
        StringBuilder builder = new StringBuilder(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(chars.length());
            char randomChar = chars.charAt(randomIndex);
            builder.append(randomChar);
        }
        return builder.toString();
    }
}
