package com.marketplace.marketplace.assembler;

import com.marketplace.marketplace.entity.base.BaseEntity;
import com.marketplace.marketplace.dto.base.BaseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.RepresentationModelAssembler;

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

    public abstract M toModel(E entity);

}
