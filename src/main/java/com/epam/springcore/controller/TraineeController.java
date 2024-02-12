package com.epam.springcore.controller;

import com.epam.springcore.dto.trainee.*;
import com.epam.springcore.dto.training.TraineeTrainingResponseDTO;
import com.epam.springcore.dto.training.TraineeTrainingCriteriaDTO;
import com.epam.springcore.dto.user.UsernameAndPasswordDTO;
import com.epam.springcore.mapper.Trainee.TraineeMapper;
import com.epam.springcore.mapper.Trainer.TrainerListMapper;
import com.epam.springcore.mapper.Training.TrainingListMapper;
import com.epam.springcore.service.TraineeService;
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
    public UsernameAndPasswordDTO addTrainee(@RequestBody TraineeRegistrationDTO traineeRegistrationDTO) {
        return traineeMapper.registrationResponse(traineeService.createTrainee(traineeMapper.registrationDTOtoTrainee(traineeRegistrationDTO)));
    }

    @PutMapping("/trainees")
    public TraineeResponseDTO updateTrainee(@RequestBody TraineeUpdateDTO traineeUpdateDTO) {
        return traineeMapper.traineeToResponseDTO(traineeService.updateTrainee(traineeMapper.updateDTOtoTrainee(traineeUpdateDTO)));
    }

    @DeleteMapping("/trainee/{username}")
    public void deleteTrainee(@PathVariable String username) {
        traineeService.deleteTraineeByUsername(username);
    }

    @PatchMapping("trainees/activate")
    public void activateTrainee(@RequestBody ActivationDTO activationDTO) {
        traineeService.activateTrainee(traineeService.findTraineeByUsername(activationDTO.getUsername()).getId());
    }

    @PatchMapping("trainees/deactivate")
    public void deactivateTrainee(@RequestBody ActivationDTO activationDTO) {
        traineeService.deactivateTrainee(traineeService.findTraineeByUsername(activationDTO.getUsername()).getId());
    }

//    @PutMapping("trainees/trainers")
//    public List<TraineesTrainerResponseDTO> updateTrainersList(@RequestBody TraineeUpdateTrainerListDTO dto) {
//        traineeService.updateTraineesTrainerList(traineeMapper.updateTrainerListDTOtoTrainee(dto));
//        return trainerMapper.trainerToTraineesResponseDTOList(traineeService.findTrainersList(dto.getUsername()));
//    }

    @GetMapping("/trainees/trainings")
    public List<TraineeTrainingResponseDTO> getTraineesTrainingList(@RequestBody TraineeTrainingCriteriaDTO dto) {
        return trainingMapper.trainingListToDto(traineeService.findTrainingByUsernameAndCriteria(dto));

    }
}
