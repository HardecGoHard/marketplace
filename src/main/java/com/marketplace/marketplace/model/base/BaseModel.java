package com.marketplace.marketplace.model.base;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.EntityModel;

import java.util.Objects;

@JsonPropertyOrder("id")
public abstract class BaseModel<M> extends EntityModel<M> {

    private Long id;

    public Long getId() {
        return id;
    }

    public BaseModel<M> setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseModel<?> baseModel = (BaseModel<?>) o;
        return Objects.equals(id, baseModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
