package com.epam.springcore.repository;

import com.epam.springcore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user based on their user ID
     *
     * @param username The username of the user
     * @return An {@code Optional} containing the user if found, or an empty {@code Optional} otherwise.
     */
    Optional<User> findUserByUsername(String username);

    /**
     * Checks if user with given username exists in a database
     *
     * @param username The username of the user
     * @return A {@code true} if user with given username exists, {@code false} otherwise
     */
    boolean existsByUsername(String username);

}
