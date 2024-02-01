package com.epam.springcore.service;

import com.epam.springcore.entity.User;

import java.util.List;

/**
 * Service interface for managing users.
 */
public interface UserService {

    /**
     * Retrieves a list of all users.
     *
     * @return A list of all users.
     */
    List<User> getAllUsers();

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return The user with the specified ID, or null if not found.
     */
    User getUserById(Long id);

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user.
     * @return The user with the specified username, or null if not found.
     */
    User getUserByUsername(String username);

    /**
     * Checks if a user with the given username exists.
     *
     * @param username The username to check for existence.
     * @return {@code true} if a user with the username exists, {@code false} otherwise.
     */
    boolean usernameExists(String username);

    /**
     * Creates a new user using the provided information.
     *
     * @param user The user to be created.
     */
    void createUser(User user);

    /**
     * Updates the information of an existing user.
     *
     * @param user The user with updated information.
     */
    void updateUser(User user);

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id The unique identifier of the user to be deleted.
     */
    void deleteUser(Long id);
}

