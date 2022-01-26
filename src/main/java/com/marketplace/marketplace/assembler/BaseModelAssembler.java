package com.marketplace.marketplace.assembler;

import com.marketplace.marketplace.entity.base.BaseEntity;
import com.marketplace.marketplace.model.base.BaseModel;
import org.springframework.beans.BeanUtils;

public abstract class BaseModelAssembler<E extends BaseEntity, M extends BaseModel<M>> {

    private final Class<M> modelType;

    protected BaseModelAssembler(Class<M> modelType) {
        this.modelType = modelType;
    }

    protected M getEntityWithId(E entity) {
        M model = BeanUtils.instantiateClass(this.modelType);
        model.setId(entity.getId());
        return model;
    }

    public abstract M toModel(E entity);

}
