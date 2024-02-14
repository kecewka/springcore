package com.epam.springcore.dto.user;

import jakarta.validation.constraints.NotBlank;

public class ActivationDTO {
    @NotBlank(message = "username is mandatory")
    public String username;
    public boolean isActive;

    public ActivationDTO() {
    }

    public ActivationDTO(String username, boolean isActive) {
        this.username = username;
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
