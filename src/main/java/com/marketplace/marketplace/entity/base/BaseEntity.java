package com.marketplace.marketplace.entity.base;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -5503397766718991017L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    @PrePersist
    public void prePersist() {
        setUuid(UUID.randomUUID().toString());
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
