package com.epam.springcore.dto.trainee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class TraineeUpdateTrainerListDTO {
    @NotBlank(message = "username is mandatory")
    private String username;
    @NotNull
    private List<String> trainers;

    public TraineeUpdateTrainerListDTO() {
    }

    public TraineeUpdateTrainerListDTO(String username, List<String> trainers) {
        this.username = username;
        this.trainers = trainers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<String> trainers) {
        this.trainers = trainers;
    }
}
