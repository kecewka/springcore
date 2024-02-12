package com.epam.springcore.controller;

import com.epam.springcore.dto.trainee.ActivationDTO;
import com.epam.springcore.dto.trainer.NotAssignedTrainerResponseDTO;
import com.epam.springcore.dto.trainer.TrainerRegistrationDTO;
import com.epam.springcore.dto.trainer.TrainerResponseDTO;
import com.epam.springcore.dto.training.TrainerTrainingCriteriaDTO;
import com.epam.springcore.dto.training.TrainerTrainingResponseDTO;
import com.epam.springcore.dto.user.UsernameAndPasswordDTO;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.mapper.Trainer.TrainerListMapper;
import com.epam.springcore.mapper.Trainer.TrainerMapper;
import com.epam.springcore.mapper.Training.TrainingListMapper;
import com.epam.springcore.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainerController {
    private final TrainerService trainerService;
    private final TrainerMapper trainerMapper;
    private final TrainerListMapper trainerListMapper;
    private final TrainingListMapper trainingListMapper;

    @Autowired
    public TrainerController(TrainerService trainerService, TrainerMapper trainerMapper, TrainerListMapper trainerListMapper, TrainingListMapper trainingListMapper) {
        this.trainerService = trainerService;
        this.trainerMapper = trainerMapper;
        this.trainerListMapper = trainerListMapper;
        this.trainingListMapper = trainingListMapper;
    }

    @GetMapping("/trainers/{username}")
    public TrainerResponseDTO getTrainer(@PathVariable String username) {
        return trainerMapper.trainerToResponseDTO(trainerService.findTrainerByUsername(username));
    }

    @PostMapping("/trainers")
    public UsernameAndPasswordDTO addTrainer(@RequestBody TrainerRegistrationDTO trainerRegistrationDTO){
        return trainerMapper.registrationResponse(trainerService.createTrainer(trainerMapper.registrationDTOtoTrainer(trainerRegistrationDTO)));
    }

    @GetMapping("/trainers")
    public List<NotAssignedTrainerResponseDTO> getNotAssignedActiveTrainers() {
        return trainerListMapper.trainerToNotAssignedDTOList(trainerService.getNotAssignedAndActiveTrainers());
    }

    @GetMapping("/trainers/trainings")
    public List<TrainerTrainingResponseDTO> getTrainersTrainingList(@RequestBody TrainerTrainingCriteriaDTO dto) {
        return trainingListMapper.trainingListToTrainerDto(trainerService.findTrainingByUsernameAndCriteria(dto));
    }

    @PatchMapping("/trainers/activate")
    public void activateTrainer(@RequestBody ActivationDTO activationDTO){
        trainerService.activateTrainer(trainerService.findTrainerByUsername(activationDTO.getUsername()).getId());
    }

    @PatchMapping("/trainers/deactivate")
    public void deactivateTrainer(@RequestBody ActivationDTO activationDTO){
        trainerService.deactivateTrainer(trainerService.findTrainerByUsername(activationDTO.getUsername()).getId());
    }
}
