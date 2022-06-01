package com.marketplace.marketplace.dto;

public class RegistrationModel {
    private String username;
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public RegistrationModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegistrationModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegistrationModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
