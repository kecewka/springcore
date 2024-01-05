package com.epam.springcore.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trainers")
public class Trainer extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TrainingType specialization;
    private List<Training> trainings;
    private List<Trainee> trainees;

    public Trainer() {
    }

    public Trainer(Long id, TrainingType specialization, List<Training> trainings, List<Trainee> trainees) {
        this.id = id;
        this.specialization = specialization;
        this.trainings = trainings;
        this.trainees = trainees;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrainingType getSpecialization() {
        return specialization;
    }

    public void setSpecialization(TrainingType specialization) {
        this.specialization = specialization;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<Trainee> trainees) {
        this.trainees = trainees;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", specialization=" + specialization +
                ", trainings=" + trainings +
                ", trainees=" + trainees +
                '}';
    }
}
