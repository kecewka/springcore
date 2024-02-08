package com.epam.springcore.controller;

import com.epam.springcore.dto.training.TrainingPostDTO;
import com.epam.springcore.mapper.Training.TrainingMapper;
import com.epam.springcore.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TrainingController {

    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;



    @Autowired
    public TrainingController(TrainingService trainingService, TrainingMapper trainingMapper) {
        this.trainingService = trainingService;
        this.trainingMapper = trainingMapper;
    }

    @PostMapping("/trainings")
    public void addTraining(@RequestBody TrainingPostDTO trainingPostDTO) {
        trainingService.createTraining(trainingMapper.dtoToTraining(trainingPostDTO));
    }
}
