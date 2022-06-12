package com.marketplace.marketplace.dto;

import com.marketplace.marketplace.dto.base.BaseModel;

public class TagDto extends BaseModel<TagDto> {

    private String name;
    private int usageCount;

    public TagDto() {
    }

    public String getName() {
        return name;
    }

    public TagDto setName(String name) {
        this.name = name;
        return this;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public TagDto setUsageCount(int usageCount) {
        this.usageCount = usageCount;
        return this;
    }
}
