package com.epam.springcore.controller;

import com.epam.springcore.dto.trainee.*;
import com.epam.springcore.dto.trainer.TraineesTrainerResponseDTO;
import com.epam.springcore.dto.training.TraineeTrainingResponseDTO;
import com.epam.springcore.dto.training.TraineeTrainingCriteriaDTO;
import com.epam.springcore.dto.user.ActivationDTO;
import com.epam.springcore.dto.user.UsernameAndPasswordDTO;
import com.epam.springcore.mapper.trainee.TraineeMapper;
import com.epam.springcore.mapper.trainer.TrainerListMapper;
import com.epam.springcore.mapper.training.TrainingListMapper;
import com.epam.springcore.service.TraineeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TraineeController {
    private final TraineeService traineeService;
    private final TraineeMapper traineeMapper;
    private final TrainerListMapper trainerMapper;
    private final TrainingListMapper trainingMapper;

    @Autowired
    public TraineeController(TraineeService traineeService, TraineeMapper traineeMapper, TrainerListMapper trainerMapper, TrainingListMapper trainingMapper) {
        this.traineeService = traineeService;
        this.traineeMapper = traineeMapper;
        this.trainerMapper = trainerMapper;
        this.trainingMapper = trainingMapper;
    }

    @GetMapping("/trainees/{username}")
    public TraineeResponseDTO getTrainee(@PathVariable String username) {
        return traineeMapper.traineeToResponseDTO(traineeService.findTraineeByUsername(username));
    }

    @PostMapping("/trainees")
    public UsernameAndPasswordDTO addTrainee(@RequestBody @Valid TraineeRegistrationDTO traineeRegistrationDTO) {
        return traineeMapper.registrationResponse(traineeService.createTrainee(traineeMapper.registrationDTOtoTrainee(traineeRegistrationDTO)));
    }

    @PutMapping("/trainees")
    public TraineeUpdateResponseDTO updateTrainee(@RequestBody @Valid TraineeUpdateRequestDTO traineeUpdateRequestDTO) {
        return traineeMapper.traineeToUpdateResponseDTO(traineeService.updateTrainee(traineeMapper.updateDTOtoTrainee(traineeUpdateRequestDTO)));
    }

    @DeleteMapping("/trainees/{username}")
    public void deleteTrainee(@PathVariable String username) {
        traineeService.deleteTraineeByUsername(username);
    }

    @PatchMapping("trainees/activate")
    public void activateTrainee(@RequestBody @Valid ActivationDTO activationDTO) {
        traineeService.activateTrainee(traineeService.findTraineeByUsername(activationDTO.getUsername()).getId());
    }

    @PatchMapping("trainees/deactivate")
    public void deactivateTrainee(@RequestBody @Valid ActivationDTO activationDTO) {
        traineeService.deactivateTrainee(traineeService.findTraineeByUsername(activationDTO.getUsername()).getId());
    }

    @PutMapping("trainees/trainers")
    public List<TraineesTrainerResponseDTO> updateTrainersList(@RequestBody @Valid TraineeUpdateTrainerListDTO dto) {
        return trainerMapper.trainerToTraineesResponseDTOList(traineeService.updateTraineesTrainerList(dto.getUsername(), dto.getTrainers()));
    }

    @GetMapping("/trainees/trainings")
    public List<TraineeTrainingResponseDTO> getTraineesTrainingList(@RequestBody @Valid TraineeTrainingCriteriaDTO dto) {
        return trainingMapper.trainingListToDto(traineeService.findTrainingByUsernameAndCriteria(dto));

    }
}