package com.epam.springcore.controller;

import com.epam.springcore.dto.trainingtype.TrainingTypeDTO;
import com.epam.springcore.entity.TrainingType;
import com.epam.springcore.mapper.trainingtype.TrainingTypeListMapper;
import com.epam.springcore.mapper.trainingtype.TrainingTypeMapper;
import com.epam.springcore.service.TrainingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainingTypeController {

    private final TrainingTypeService trainingTypeService;
    private final TrainingTypeListMapper trainingTypeListMapper;
    private final TrainingTypeMapper trainingTypeMapper;

    @Autowired
    public TrainingTypeController(TrainingTypeService trainingTypeService, TrainingTypeListMapper trainingTypeListMapper, TrainingTypeMapper trainingTypeMapper) {
        this.trainingTypeService = trainingTypeService;
        this.trainingTypeListMapper = trainingTypeListMapper;
        this.trainingTypeMapper = trainingTypeMapper;
    }


    @GetMapping("/training-types")
    public List<TrainingTypeDTO> getTrainingTypes(){
        return trainingTypeListMapper.toDtoList(trainingTypeService.findAllTrainingTypes());
    }

    @GetMapping("/training-types/{name}")
    public TrainingType getTrainingTypeByName(@PathVariable String name){
        return trainingTypeService.findByName(name);
        //return trainingTypeMapper.toDto(trainingTypeService.findByName(name));
    }
}
