package com.epam.springcore.feign;

import com.epam.springcore.config.ReportServiceConfiguration;
import com.epam.springcore.dto.reportservice.TrainingRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "report-service", path = "/api/trainings/records", configuration = ReportServiceConfiguration.class)
public interface ReportService {
    @PostMapping
    ResponseEntity<?> recordTraining(@RequestBody TrainingRequestDTO dto);
}
