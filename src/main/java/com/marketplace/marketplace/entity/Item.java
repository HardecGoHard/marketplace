package com.marketplace.marketplace.entity;


import com.marketplace.marketplace.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Item extends BaseEntity {
    private static final long serialVersionUID = -3523052237621832929L;

    private String name;

    private String description;
    @Column(name = "owner_id", insertable = false, updatable = false)
    private Long ownerId;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "item")
    private Set<Comment> comments;

    @ManyToMany
    @JoinTable(name = "item_tag", joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    private Double price;

    private String avatar;

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

    public Double getPrice() {
        return price;
    }

    public Item setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Item setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public Item setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Item setComments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }
}
