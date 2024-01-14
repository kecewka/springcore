package com.epam.springcore.controller;

import com.epam.springcore.service.TraineeService;
import com.epam.springcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class Controller {

    private final UserService userService;
    private final TraineeService traineeService;

    @Autowired
    public Controller(UserService userService, TraineeService traineeService) {
        this.userService = userService;
        this.traineeService = traineeService;
    }

//    @GetMapping
//    public String asd(){
//        return userService.getUserById(1L).getFirstName();
//    }

    @GetMapping
    public String qwe(){
        return traineeService.findTraineeById(1L).toString();
    }
}
