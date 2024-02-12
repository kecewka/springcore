package com.epam.springcore.dto.trainer;

public class NotAssignedTrainerResponseDTO {
    private String username;
    private String firstName;
    private String lastName;
    private Long specialization;

    public NotAssignedTrainerResponseDTO() {
    }

    public NotAssignedTrainerResponseDTO(String username, String firstName, String lastName, Long specialization) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
