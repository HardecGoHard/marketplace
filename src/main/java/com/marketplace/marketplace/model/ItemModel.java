package com.marketplace.marketplace.model;

import com.marketplace.marketplace.model.base.BaseModel;

import java.util.Set;

public class ItemModel extends BaseModel<ItemModel> {
    private String name;

    private String description;

    private String ownerUuid;

    private Set<String> tags;

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

    public String getOwnerUuid() {
        return ownerUuid;
    }

    public ItemModel setOwnerUuid(String ownerUuid) {
        this.ownerUuid = ownerUuid;
        return this;
    }

    public Set<String> getTags() {
        return tags;
    }

    public ItemModel setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }
}
