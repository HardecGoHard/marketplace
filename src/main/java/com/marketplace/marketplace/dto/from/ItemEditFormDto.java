package com.marketplace.marketplace.dto.from;

public class ItemEditFormDto {
    private String name;
    private String description;
    private Double price;

    public String getName() {
        return name;
    }

    public ItemEditFormDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemEditFormDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ItemEditFormDto setPrice(Double price) {
        this.price = price;
        return this;
    }
}
