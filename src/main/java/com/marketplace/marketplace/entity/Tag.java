package com.marketplace.marketplace.entity;

import com.marketplace.marketplace.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Tag extends BaseEntity {

    private static final long serialVersionUID = 604934841905852817L;

    @Column(unique=true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Item> items;

    public Tag() {
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Tag setItems(Set<Item> items) {
        this.items = items;
        return this;
    }
}
