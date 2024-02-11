package com.epam.springcore.dto.training;

import java.time.LocalDate;

public class TrainingCriteriaDTO {
    private String username;
    private LocalDate from;
    private LocalDate to;
    private String trainerName;
    private String trainingType;

    public TrainingCriteriaDTO() {
    }

    public TrainingCriteriaDTO(String username, LocalDate from, LocalDate to, String trainerName, String trainingType) {
        this.username = username;
        this.from = from;
        this.to = to;
        this.trainerName = trainerName;
        this.trainingType = trainingType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }
}
