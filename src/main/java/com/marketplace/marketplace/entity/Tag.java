package com.marketplace.marketplace.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Item> items;

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public Tag setId(Long id) {
        this.id = id;
        return this;
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
