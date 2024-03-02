package com.epam.springcore.service.impl;

import com.epam.springcore.config.UserInfoDetails;
import com.epam.springcore.entity.User;
import com.epam.springcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = repository.findUserByUsername(username);

        // Converting userDetail to UserDetails
        return userDetail.map(user -> {
            return new UserInfoDetails(user);
        }).orElseThrow(() -> new UsernameNotFoundException("User not found " + username));


    }

}