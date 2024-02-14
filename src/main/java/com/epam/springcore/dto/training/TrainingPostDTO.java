package com.epam.springcore.dto.training;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class TrainingPostDTO {
    private Long id;

    @NotBlank(message = "Trainee username is mandatory")
    private String traineeUsername;
    @NotBlank(message = "Trainer username is mandatory")
    private String trainerUsername;
    @NotBlank(message = "Training name is mandatory")
    private String trainingName;
    @NotNull(message = "Training date is mandatory")
    private LocalDate trainingDate;
    @Positive
    private Long trainingDuration;
    @NotBlank(message = "Training Type is mandatory")
    private String trainingTypeName;

    public TrainingPostDTO() {
    }

    public TrainingPostDTO(Long id, String traineeUsername, String trainerUsername, String trainingName, LocalDate trainingDate, Long trainingDuration, String trainingTypeName) {
        this.id = id;
        this.traineeUsername = traineeUsername;
        this.trainerUsername = trainerUsername;
        this.trainingName = trainingName;
        this.trainingDate = trainingDate;
        this.trainingDuration = trainingDuration;
        this.trainingTypeName = trainingTypeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTraineeUsername() {
        return traineeUsername;
    }

    public void setTraineeUsername(String traineeUsername) {
        this.traineeUsername = traineeUsername;
    }

    public String getTrainerUsername() {
        return trainerUsername;
    }

    public void setTrainerUsername(String trainerUsername) {
        this.trainerUsername = trainerUsername;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(LocalDate trainingDate) {
        this.trainingDate = trainingDate;
    }

    public Long getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(Long trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public String getTrainingTypeName() {
        return trainingTypeName;
    }

    public void setTrainingTypeName(String trainingTypeName) {
        this.trainingTypeName = trainingTypeName;
    }
}
