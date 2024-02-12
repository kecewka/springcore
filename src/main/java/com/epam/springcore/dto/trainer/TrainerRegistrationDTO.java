package com.epam.springcore.dto.trainer;

public class TrainerRegistrationDTO {
    private String firstName;
    private String lastName;
    private Long specialization;

    public TrainerRegistrationDTO() {
    }

    public TrainerRegistrationDTO(String firstName, String lastName, Long specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Long specialization) {
        this.specialization = specialization;
    }
}
