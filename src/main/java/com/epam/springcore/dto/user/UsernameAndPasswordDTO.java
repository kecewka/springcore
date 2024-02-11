package com.epam.springcore.dto.user;

public class UsernameAndPasswordDTO {
    private String username;
    private String password;

    public UsernameAndPasswordDTO() {
    }

    public UsernameAndPasswordDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
