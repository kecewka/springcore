package com.epam.springcore.service.impl;

import com.epam.springcore.dto.reportservice.TrainingRequestDTO;
import com.epam.springcore.entity.ActionType;
import com.epam.springcore.entity.Training;
import com.epam.springcore.exception.ServiceNotAvailableException;
import com.epam.springcore.exception.TrainingNotFoundException;
import com.epam.springcore.feign.ReportService;
import com.epam.springcore.repository.TrainingRepository;
import com.epam.springcore.service.TrainingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;
    private final ReportService reportService;
    private static final Logger LOGGER = LogManager.getLogger(TrainingServiceImpl.class);
    static final String EXCHANGE_NAME = "reportExchange";
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, ReportService reportService, RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.trainingRepository = trainingRepository;
        this.reportService = reportService;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }


    @Override
    public List<Training> getAllTrainings() {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Getting all trainings", transactionId);
        return trainingRepository.findAll();
    }

    @Override
    public Training findTrainingById(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Finding training with ID: {}", transactionId, id);

        return trainingRepository.findById(id).orElseThrow(() -> new TrainingNotFoundException("Training with id: " + id + " not found"));
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "save-training", fallbackMethod = "failSave")
    public void createTraining(Training training) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Creating training: {}", transactionId, training);
        trainingRepository.save(training);

        //sendRequest(training, ActionType.ADD);
        sendRabbitRequest(training, ActionType.ADD);

    }

    @Override
    public void updateTraining(Training training) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Updating training: {}", transactionId, training);
        trainingRepository.save(training);
    }

    @Override
    public void deleteTraining(Long id) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Deleting training with ID: {}", transactionId, id);
        //sendRequest(findTrainingById(id), ActionType.DELETE);
        sendRabbitRequest(findTrainingById(id), ActionType.DELETE);
        trainingRepository.deleteById(id);

    }

    public void sendRequest(Training training, ActionType actionType) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Sending request to Report Microservice", transactionId);
        reportService.recordTraining(new TrainingRequestDTO(
                training.getTrainer().getUser().getUsername(),
                training.getTrainer().getUser().getFirstName(),
                training.getTrainer().getUser().getLastName(),
                training.getTrainer().getUser().isActive(),
                training.getTrainingDate(),
                training.getTrainingDuration(),
                actionType
        ));
        LOGGER.info("[Transaction ID: {}] Request has been sent", transactionId);
    }

    public void sendRabbitRequest(Training training, ActionType actionType) {
        String transactionId = MDC.get("transactionId");
        LOGGER.info("[Transaction ID: {}] Sending request to Report Microservice", transactionId);
        TrainingRequestDTO requestDTO = new TrainingRequestDTO(
                training.getTrainer().getUser().getUsername(),
                training.getTrainer().getUser().getFirstName(),
                training.getTrainer().getUser().getLastName(),
                training.getTrainer().getUser().isActive(),
                training.getTrainingDate(),
                training.getTrainingDuration(),
                actionType
        );

        String jsonPayload;
        try {
            jsonPayload = objectMapper.writeValueAsString(requestDTO);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error serializing request to JSON: {}", e.getMessage());
            return;
        }

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("transactionId", transactionId);
        messageProperties.setHeader(HttpHeaders.AUTHORIZATION, MDC.get(HttpHeaders.AUTHORIZATION));

        Message message = MessageBuilder.withBody(jsonPayload.getBytes()).andProperties(messageProperties).build();


        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key", message);
    }

    public void failSave(Throwable exception) {
        throw new ServiceNotAvailableException("Service is unavailable right now please try again later");
    }

}
