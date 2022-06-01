package com.marketplace.marketplace.dto.base;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@JsonPropertyOrder("id")
public abstract class BaseModel<M extends RepresentationModel<? extends M>> extends RepresentationModel<M> {

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
