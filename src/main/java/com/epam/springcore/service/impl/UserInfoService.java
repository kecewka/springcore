package com.epam.springcore.service.impl;

import com.epam.springcore.config.UserInfoDetails;
import com.epam.springcore.entity.User;
import com.epam.springcore.exception.VerificationException;
import com.epam.springcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (loginAttemptService.isBlocked(username)) {
            throw new VerificationException("user is blocked exceeded 3 attempts");
        }

        Optional<User> userDetail = repository.findUserByUsername(username);

        // Converting userDetail to UserDetails
        return userDetail.map(user -> new UserInfoDetails(user)).orElseThrow(() -> new UsernameNotFoundException("User not found " + username));


    }

}