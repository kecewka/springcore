package com.epam.springcore.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "training_types")
public class TrainingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private List<Trainer> trainers;
    private List<Training> trainings;

    public TrainingType() {
    }

    public TrainingType(Long id, String name, List<Trainer> trainers, List<Training> trainings) {
        this.id = id;
        this.name = name;
        this.trainers = trainers;
        this.trainings = trainings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trainer> getTrainersList() {
        return trainers;
    }

    public void setTrainersList(List<Trainer> trainersList) {
        this.trainers = trainersList;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    @Override
    public String toString() {
        return "TrainingType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", trainers=" + trainers +
                ", trainings=" + trainings +
                '}';
    }
}
