package com.epam.springcore.dto.training;

import java.time.LocalDate;

public class TrainerTrainingCriteriaDTO {
    private String username;
    private LocalDate from;
    private LocalDate to;
    private String traineeName;

    public TrainerTrainingCriteriaDTO() {
    }

    public TrainerTrainingCriteriaDTO(String username, LocalDate from, LocalDate to, String traineeName) {
        this.username = username;
        this.from = from;
        this.to = to;
        this.traineeName = traineeName;
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

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String trainerName) {
        this.traineeName = trainerName;
    }
}
