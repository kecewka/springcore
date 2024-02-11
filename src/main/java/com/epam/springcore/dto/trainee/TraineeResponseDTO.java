package com.epam.springcore.dto.trainee;

import com.epam.springcore.dto.trainer.TraineesTrainerResponseDTO;

import java.time.LocalDate;
import java.util.List;

public class TraineeResponseDTO {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private boolean isActive;
    private List<TraineesTrainerResponseDTO> trainers;

    public TraineeResponseDTO(String firstName, String lastName, LocalDate dateOfBirth, String address, boolean isActive, List<TraineesTrainerResponseDTO> trainers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.isActive = isActive;
        this.trainers = trainers;
    }

    public TraineeResponseDTO() {
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<TraineesTrainerResponseDTO> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<TraineesTrainerResponseDTO> trainers) {
        this.trainers = trainers;
    }
}
