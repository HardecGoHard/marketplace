package com.marketplace.marketplace.model;

import com.marketplace.marketplace.model.base.BaseModel;

import java.util.Set;

public class ItemModel extends BaseModel<ItemModel> {
    private String name;

    private String description;

    private Long ownerId;

    private Set<TagModel> tags;

    public String getName() {
        return name;
    }

    public ItemModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public ItemModel setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Set<TagModel> getTags() {
        return tags;
    }

    public ItemModel setTags(Set<TagModel> tags) {
        this.tags = tags;
        return this;
    }
}
