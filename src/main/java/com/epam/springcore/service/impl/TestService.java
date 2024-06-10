package com.epam.springcore.service.impl;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.epam.springcore.entity.Trainee;
import com.epam.springcore.repository.TraineeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestService {

    @Value("${aws.queueName}")
    private String queueName;
    @Value("${aws.queue.url}")
    private String queueUrl;

    private final AmazonSQS amazonSQSAsync;
    private final ObjectMapper objectMapper;
    private final TraineeRepository traineeRepository;

    @Autowired
    public TestService(AmazonSQS amazonSQSClient, ObjectMapper objectMapper, TraineeRepository traineeRepository) {
        this.amazonSQSAsync = amazonSQSClient;
        this.objectMapper = objectMapper;
        this.traineeRepository = traineeRepository;
    }

    public List<Trainee> findAll() {
        return traineeRepository.findAll();
    }

    public void publishMessage(String id) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody("asd " + LocalDateTime.now());
        amazonSQSAsync.sendMessage(sendMessageRequest);
    }

}
