package com.marketplace.marketplace.dto.from;

import org.springframework.web.multipart.MultipartFile;

public class ItemFormDto {
    private String name;
    private String description;
    private Double price;
    private MultipartFile avatar;

    public String getName() {
        return name;
    }

    public ItemFormDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemFormDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ItemFormDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public ItemFormDto setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
        return this;
    }
}
