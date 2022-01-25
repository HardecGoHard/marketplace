package com.marketplace.marketplace.entity;


import com.marketplace.marketplace.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Item extends BaseEntity<Long> {
    private static final long serialVersionUID = -3523052237621832929L;

    private String name;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "item_tag", joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Item setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Item setTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }
}
