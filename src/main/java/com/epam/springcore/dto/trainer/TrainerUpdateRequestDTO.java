package com.epam.springcore.dto.trainer;

public class TrainerUpdateRequestDTO {
    private String username;
    private String firstName;
    private String lastName;
    private Long specialization;
    private boolean isActive;

    public TrainerUpdateRequestDTO() {
    }

    public TrainerUpdateRequestDTO(String username, String firstName, String lastName, Long specialization, boolean isActive) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.isActive = isActive;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
