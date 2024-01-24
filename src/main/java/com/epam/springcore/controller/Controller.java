package com.epam.springcore.controller;

import com.epam.springcore.entity.*;
import com.epam.springcore.service.TraineeService;
import com.epam.springcore.service.TrainerService;
import com.epam.springcore.service.TrainingService;
import com.epam.springcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;


@RestController
@RequestMapping("/api")
public class Controller {
    private final UserService userService;
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    @Autowired
    public Controller(UserService userService, TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.userService = userService;
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }

    public Trainee createTrainee(String firstname, String lastname, LocalDate dateOfBirth, String address) {
        User user = new User(1L, firstname, lastname, "", "", true);
        Trainee trainee = new Trainee(1L, dateOfBirth, address, user, null, null);
        traineeService.createTrainee(trainee);
        return trainee;
    }


    @GetMapping
    public void asd() {
        traineeService.changeTraineePassword(2L, "password123");

    }

}
