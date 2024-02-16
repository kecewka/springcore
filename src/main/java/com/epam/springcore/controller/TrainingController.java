package com.epam.springcore.controller;

import com.epam.springcore.dto.training.TrainingPostDTO;
import com.epam.springcore.mapper.training.TrainingMapper;
import com.epam.springcore.service.TrainingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void addTraining(@RequestBody @Valid TrainingPostDTO trainingPostDTO) {
        trainingService.createTraining(trainingMapper.dtoToTraining(trainingPostDTO));
    }
}
