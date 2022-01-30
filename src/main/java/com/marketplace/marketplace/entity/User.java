package com.marketplace.marketplace.entity;

import com.marketplace.marketplace.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private static final long serialVersionUID = 3010316090317258607L;

    private String name;
    private String surname;
    private String username;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;


    @OneToMany(mappedBy = "owner")
    private Set<Item> ownedItems;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private Date lastVisit;

    public User() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public User setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
        return this;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public User setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }


    public Set<Item> getOwnedItems() {
        return ownedItems;
    }

    public User setOwnedItems(Set<Item> ownedItems) {
        this.ownedItems = ownedItems;
        return this;
    }
}
