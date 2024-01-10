package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.Trainer;
import com.epam.springcore.repository.TrainerRepository;
import com.epam.springcore.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer findTrainerById(Long id) {
        return trainerRepository.findById(id);
    }

    @Override
    public void createTrainer(Trainer trainer) {
        trainerRepository.createTrainer(trainer);
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        trainerRepository.updateTrainer(trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        trainerRepository.deleteTrainer(id);
    }
}
