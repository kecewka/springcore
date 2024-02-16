package com.epam.springcore.dto.training;

import com.epam.springcore.dto.trainingtype.TrainingTypeDTO;

import java.time.LocalDate;

public class TrainerTrainingResponseDTO {
    private String trainingName;
    private LocalDate trainingDate;
    private TrainingTypeDTO trainingType;
    private Long trainingDuration;
    private String traineeName;

    public TrainerTrainingResponseDTO() {
    }

    public TrainerTrainingResponseDTO(String trainingName, LocalDate trainingDate, TrainingTypeDTO trainingType, Long trainingDuration, String traineeName) {
        this.trainingName = trainingName;
        this.trainingDate = trainingDate;
        this.trainingType = trainingType;
        this.trainingDuration = trainingDuration;
        this.traineeName = traineeName;
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

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }
}
