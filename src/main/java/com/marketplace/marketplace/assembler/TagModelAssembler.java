package com.marketplace.marketplace.assembler;

import com.marketplace.marketplace.entity.Tag;
import com.marketplace.marketplace.model.TagModel;
import org.springframework.stereotype.Component;

//TODO think about logic of counting items, will it fetch all items?
// maybe write count method in repository
@Component
public class TagModelAssembler extends BaseModelAssembler<Tag, TagModel> {

    protected TagModelAssembler() {
        super(TagModel.class);
    }

    @Override
    public TagModel toModel(Tag entity) {
        TagModel model = this.getEntityWithId(entity);

        model.setName(entity.getName());
        if(entity.getItems() != null) {
            model.setUsageCount(entity.getItems().size());
        } else {
            model.setUsageCount(0);
        }
        return model;
    }
}
