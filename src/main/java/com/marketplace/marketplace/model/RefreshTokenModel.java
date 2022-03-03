package com.marketplace.marketplace.model;

public class RefreshTokenModel {
    private String username;
    private Long id;
    private String refreshCode;

    public RefreshTokenModel() {
    }

    public String getUsername() {
        return username;
    }

    public RefreshTokenModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RefreshTokenModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRefreshCode() {
        return refreshCode;
    }

    public RefreshTokenModel setRefreshCode(String refreshCode) {
        this.refreshCode = refreshCode;
        return this;
    }
}
