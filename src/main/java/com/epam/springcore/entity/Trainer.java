package com.epam.springcore.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trainers")
public class Trainer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private User user;
    private TrainingType specialization;
    private List<Training> trainings;
    private List<Trainee> trainees;

    public Trainer() {
    }

    public Trainer(Long id, TrainingType specialization, User user, List<Training> trainings, List<Trainee> trainees) {
        this.id = id;
        this.specialization = specialization;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", user=" + user +
                ", trainings=" + trainings +
                ", trainees=" + trainees +
                '}';
    }
}
