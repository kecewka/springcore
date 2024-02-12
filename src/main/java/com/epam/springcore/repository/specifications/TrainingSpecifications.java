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

    public static Specification<Training> hasTrainingType(String trainingType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("trainingType").get("name"), trainingType);
    }

    public static Specification<Training> hasTrainingDate(LocalDate trainingDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("trainingDate"), trainingDate);
    }

    public static Specification<Training> hasTrainingDateBetween(LocalDate fromDate, LocalDate toDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("trainingDate"), fromDate, toDate);
    }

    public static Specification<Training> hasTrainingDateAfter(LocalDate fromDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("trainingDate"), fromDate);
    }

    public static Specification<Training> hasTrainingDateBefore(LocalDate toDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("trainingDate"), toDate);
    }

}
