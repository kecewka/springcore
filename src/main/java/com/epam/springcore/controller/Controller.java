package com.epam.springcore.controller;

import com.epam.springcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String asd(){
        return userService.getUserById(1L).getFirstName();
    }
}
