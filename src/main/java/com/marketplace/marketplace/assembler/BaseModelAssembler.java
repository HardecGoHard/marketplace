package com.marketplace.marketplace.assembler;

import com.marketplace.marketplace.entity.base.BaseEntity;
import com.marketplace.marketplace.model.base.BaseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BaseModelAssembler<E extends BaseEntity, M extends BaseModel<M>>
        implements RepresentationModelAssembler<E, M> {

    private final Class<M> modelType;


    public BaseModelAssembler(Class<M> modelType) {
        this.modelType = modelType;
    }

    public M getEntityWithId(E entity) {
        M model = BeanUtils.instantiateClass(this.modelType);
        model.setId(entity.getId());
        return model;
    }

    public List<M> toModels(Iterable<E> entities) {
        Objects.requireNonNull(entities, "Entities must not be null!");
        List<M> result = new ArrayList<>();

        for (E entity : entities) {
            result.add(this.toModel(entity));
        }

        return result;
    }

    public PagedModel<M> toPagedModel(Page<E> page) {
        List<M> list = toModels(page.getContent());
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
                page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());

        return PagedModel.of(list, metadata);
    }

    public abstract M toModel(E entity);

}
