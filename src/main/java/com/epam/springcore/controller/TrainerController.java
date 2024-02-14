package com.epam.springcore.controller;

import com.epam.springcore.dto.user.ActivationDTO;
import com.epam.springcore.dto.trainer.*;
import com.epam.springcore.dto.training.TrainerTrainingCriteriaDTO;
import com.epam.springcore.dto.training.TrainerTrainingResponseDTO;
import com.epam.springcore.dto.user.UsernameAndPasswordDTO;
import com.epam.springcore.mapper.Trainer.TrainerListMapper;
import com.epam.springcore.mapper.Trainer.TrainerMapper;
import com.epam.springcore.mapper.Training.TrainingListMapper;
import com.epam.springcore.service.TrainerService;
import jakarta.validation.Valid;
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
    public UsernameAndPasswordDTO addTrainer(@RequestBody @Valid TrainerRegistrationDTO trainerRegistrationDTO) {
        return trainerMapper.registrationResponse(trainerService.createTrainer(trainerMapper.registrationDTOtoTrainer(trainerRegistrationDTO)));
    }

    @PutMapping("/trainers")
    public TrainerUpdateResponseDTO updateTrainer(@RequestBody @Valid TrainerUpdateRequestDTO trainerUpdateRequestDTO) {
        return trainerMapper.trainerToUpdateResponseDTO(trainerService.updateTrainer(trainerMapper.updateDTOtoTrainer(trainerUpdateRequestDTO)));
    }

    @GetMapping("/trainers")
    public List<NotAssignedTrainerResponseDTO> getNotAssignedActiveTrainers() {
        return trainerListMapper.trainerToNotAssignedDTOList(trainerService.getNotAssignedAndActiveTrainers());
    }

    @GetMapping("/trainers/trainings")
    public List<TrainerTrainingResponseDTO> getTrainersTrainingList(@RequestBody @Valid TrainerTrainingCriteriaDTO dto) {
        return trainingListMapper.trainingListToTrainerDto(trainerService.findTrainingByUsernameAndCriteria(dto));
    }

    @PatchMapping("/trainers/activate")
    public void activateTrainer(@RequestBody @Valid ActivationDTO activationDTO) {
        trainerService.activateTrainer(trainerService.findTrainerByUsername(activationDTO.getUsername()).getId());
    }

    @PatchMapping("/trainers/deactivate")
    public void deactivateTrainer(@RequestBody @Valid ActivationDTO activationDTO) {
        trainerService.deactivateTrainer(trainerService.findTrainerByUsername(activationDTO.getUsername()).getId());
    }
}
