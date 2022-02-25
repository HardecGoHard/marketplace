package com.marketplace.marketplace.model;


public class LoginModel {

    private String username;

    private String password;

    public LoginModel() {
    }

    public String getUsername() {
        return username;
    }

    public LoginModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
