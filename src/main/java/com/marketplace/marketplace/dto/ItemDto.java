package com.marketplace.marketplace.dto;

import com.marketplace.marketplace.dto.base.BaseModel;

import java.util.Set;

public class ItemDto extends BaseModel<ItemDto> {
    private String name;

    private String description;

    private Long ownerId;

    private Set<TagDto> tags;

    private Double price;

    private String avatar;

    public String getName() {
        return name;
    }

    public ItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public ItemDto setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public ItemDto setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Set<TagDto> getTags() {
        return tags;
    }

    public ItemDto setTags(Set<TagDto> tags) {
        this.tags = tags;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ItemDto setPrice(Double price) {
        this.price = price;
        return this;
    }
}
