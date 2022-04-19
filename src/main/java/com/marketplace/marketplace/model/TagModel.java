package com.marketplace.marketplace.model;

import com.marketplace.marketplace.model.base.BaseModel;

public class TagModel extends BaseModel<TagModel> {

    private String name;
    private int usageCount;

    public TagModel() {
    }

    public String getName() {
        return name;
    }

    public TagModel setName(String name) {
        this.name = name;
        return this;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public TagModel setUsageCount(int usageCount) {
        this.usageCount = usageCount;
        return this;
    }
}
