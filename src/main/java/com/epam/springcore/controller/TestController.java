package com.epam.springcore.controller;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.service.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/findall")
    public List<Trainee> findAll(){
        testService.publishMessage("1");
        return testService.findAll();
    }
}
