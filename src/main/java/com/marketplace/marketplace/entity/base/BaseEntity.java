package com.marketplace.marketplace.entity.base;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = -5503397766718991017L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    private String uuid;

    @PrePersist
    public void prePersist() {
        setUuid(UUID.randomUUID().toString());
    }

    public ID getId() {
        return id;
    }

    public BaseEntity<ID> setId(ID id) {
        this.id = id;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public BaseEntity<ID> setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
