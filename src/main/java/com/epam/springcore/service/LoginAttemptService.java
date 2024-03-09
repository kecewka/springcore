package com.epam.springcore.service;

public interface LoginAttemptService {
    /**
     * Records a failed login attempt for the specified username.
     *
     * @param username The username for which the login attempt failed.
     */
    void loginFailed(String username);

    /**
     * Checks if the specified username is currently blocked due to excessive failed login attempts.
     *
     * @param username The username to check for blocking status.
     * @return True if the username is blocked, false otherwise.
     */
    boolean isBlocked(String username);

}
