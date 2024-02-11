package com.epam.springcore.service.Impl;

import com.epam.springcore.entity.TrainingType;
import com.epam.springcore.repository.TrainingTypeRepository;
import com.epam.springcore.service.TrainingTypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {

    private final Logger LOGGER = LogManager.getLogger(TrainingTypeService.class);

    private final TrainingTypeRepository trainingTypeRepository;

    @Autowired
    public TrainingTypeServiceImpl(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    @Override
    public List<TrainingType> findAllTrainingTypes() {
        LOGGER.info("Getting all training types");
        return trainingTypeRepository.findAll();
    }

    @Override
    @Transactional
    public TrainingType findByName(String name) {
        LOGGER.info("Finding training with name: {}", name);
        return trainingTypeRepository.findByName(name).orElseThrow();
    }
}
