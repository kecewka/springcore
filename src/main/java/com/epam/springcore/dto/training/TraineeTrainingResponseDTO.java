package com.epam.springcore.dto.training;

import com.epam.springcore.dto.trainingtype.TrainingTypeDTO;

import java.time.LocalDate;

public class TraineeTrainingResponseDTO {
    private String trainingName;
    private LocalDate trainingDate;
    private TrainingTypeDTO trainingType;
    private Long trainingDuration;
    private String trainerName;

    public TraineeTrainingResponseDTO() {
    }

    public TraineeTrainingResponseDTO(String trainingName, LocalDate trainingDate, TrainingTypeDTO trainingType, Long trainingDuration, String trainerName) {
        this.trainingName = trainingName;
        this.trainingDate = trainingDate;
        this.trainingType = trainingType;
        this.trainingDuration = trainingDuration;
        this.trainerName = trainerName;
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

    public TrainingTypeDTO getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingTypeDTO trainingType) {
        this.trainingType = trainingType;
    }

    public Long getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(Long trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }
}
