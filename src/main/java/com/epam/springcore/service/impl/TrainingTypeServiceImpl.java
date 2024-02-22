package com.epam.springcore.service.impl;

import com.epam.springcore.entity.TrainingType;
import com.epam.springcore.exception.TrainingTypeNotFoundException;
import com.epam.springcore.repository.TrainingTypeRepository;
import com.epam.springcore.service.TrainingTypeService;
import jakarta.transaction.Transactional;
import org.slf4j.MDC;
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
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Getting all training types", transactionId);
        return trainingTypeRepository.findAll();
    }

    @Override
    @Transactional
    public TrainingType findByName(String name) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Finding training type with name: {}", transactionId, name);
        return trainingTypeRepository.findByName(name).orElseThrow(() -> new TrainingTypeNotFoundException("Training type with name: " + name + " not found"));
    }

    @Override
    public TrainingType findById(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Finding training type with id: {}", transactionId, id);
        return trainingTypeRepository.findById(id).orElseThrow(() -> new TrainingTypeNotFoundException("Training type with id: " + id + " not found"));
    }
}
