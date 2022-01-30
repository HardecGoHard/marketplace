package com.marketplace.marketplace.entity.base;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -5503397766718991017L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private Date createdAt;

    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        setUuid(UUID.randomUUID().toString());
        setCreatedAt(new Date());
    }

    @PreUpdate
    public void prePersistUuid() {
       setUpdatedAt(new Date());
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public BaseEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public BaseEntity setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BaseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public BaseEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
}
