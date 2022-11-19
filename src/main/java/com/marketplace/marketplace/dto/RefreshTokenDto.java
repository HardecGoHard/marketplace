package com.marketplace.marketplace.dto;

public class RefreshTokenDto {
    private String username;
    private Long id;
    private String refreshCode;

    public RefreshTokenDto() {
    }

    public String getUsername() {
        return username;
    }

    public RefreshTokenDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RefreshTokenDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRefreshCode() {
        return refreshCode;
    }

    public RefreshTokenDto setRefreshCode(String refreshCode) {
        this.refreshCode = refreshCode;
        return this;
    }
}
