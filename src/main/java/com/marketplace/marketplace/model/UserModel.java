package com.marketplace.marketplace.model;

import com.marketplace.marketplace.model.base.BaseModel;

public class UserModel extends BaseModel<UserModel> {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;

    public String getName() {
        return name;
    }

    public UserModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserModel setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public UserModel setId(Long id) {
        this.id = id;
        return this;
    }

    public UserModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserModel setEmail(String email) {
        this.email = email;
        return this;
    }

}
