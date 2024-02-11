package com.epam.springcore.repository.specifications;

import com.epam.springcore.entity.Training;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TrainingSpecifications {

    public static Specification<Training> hasTraineeId(Long traineeId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("trainee").get("id"), traineeId);
    }

    public static Specification<Training> hasTrainerId(Long trainerId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("trainer").get("id"), trainerId);
    }

    public static Specification<Training> hasTrainingName(String trainingName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("trainingName"), trainingName);
    }

    public static Specification<Training> hasTrainingDate(LocalDate trainingDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("trainingDate"), trainingDate);
    }


}
