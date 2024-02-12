package com.epam.springcore.dto.trainer;

import com.epam.springcore.dto.trainee.TrainersTraineeDTO;

import java.util.List;

public class TrainerResponseDTO {
    private String firstName;
    private String lastName;
    private Long specialization;
    private boolean isActive;
    private List<TrainersTraineeDTO> trainees;

    public TrainerResponseDTO() {
    }

    public TrainerResponseDTO(String firstName, String lastName, Long specialization, boolean isActive, List<TrainersTraineeDTO> trainees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.isActive = isActive;
        this.trainees = trainees;
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
