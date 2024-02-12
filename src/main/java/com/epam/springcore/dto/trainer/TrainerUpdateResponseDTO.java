package com.epam.springcore.dto.trainer;

import com.epam.springcore.dto.trainee.TrainersTraineeDTO;

import java.util.List;

public class TrainerUpdateResponseDTO {
    private String username;
    private String firstName;
    private String lastName;
    private Long specialization;
    private boolean isActive;
    private List<TrainersTraineeDTO> trainees;

    public TrainerUpdateResponseDTO() {
    }

    public TrainerUpdateResponseDTO(String username, String firstName, String lastName, Long specialization, boolean isActive, List<TrainersTraineeDTO> trainees) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.isActive = isActive;
        this.trainees = trainees;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Long specialization) {
        this.specialization = specialization;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<TrainersTraineeDTO> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<TrainersTraineeDTO> trainees) {
        this.trainees = trainees;
    }
}
