package com.marketplace.marketplace.dto.from;

public class EditProfileDto {
    private String name;
    private String surname;
    private String email;

    public String getName() {
        return name;
    }

    public EditProfileDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public EditProfileDto setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EditProfileDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
