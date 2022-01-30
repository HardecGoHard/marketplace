package com.marketplace.marketplace.model;

public class LoginFormModel {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public LoginFormModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginFormModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
