package com.marketplace.marketplace.assembler;

import com.marketplace.marketplace.controller.TagController;
import com.marketplace.marketplace.entity.Tag;
import com.marketplace.marketplace.model.TagModel;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class TagModelAssembler extends BaseModelAssembler<Tag, TagModel> {

    protected TagModelAssembler() {
        super(TagModel.class);
    }

    @Override
    public TagModel toModel(Tag entity) {
        TagModel model = this.getEntityWithId(entity);

        model.setName(entity.getName());

        if (Objects.nonNull(entity.getItems()) && !entity.getItems().isEmpty())
            model.setUsageCount(entity.getItems().size());
        else
            model.setUsageCount(0);

        model.add(linkTo(methodOn(TagController.class).getItemById(entity.getId())).withSelfRel());
        return model;
    }


}
