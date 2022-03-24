package com.marketplace.marketplace.assembler;

import com.marketplace.marketplace.controller.ItemController;
import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.model.ItemModel;
import com.marketplace.marketplace.model.TagModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//TODO make item model return TAGS not just as names, but as TagModel
@Component
public class ItemModelAssembler extends BaseModelAssembler<Item, ItemModel> {

    private final TagModelAssembler tagModelAssembler;

    protected ItemModelAssembler(TagModelAssembler tagModelAssembler) {
        super(ItemModel.class);
        this.tagModelAssembler = tagModelAssembler;
    }

    @Override
    public ItemModel toModel(Item entity) {
        ItemModel itemModel = super.getEntityWithId(entity);

        itemModel.setDescription(entity.getDescription());
        itemModel.setName(entity.getName());
        itemModel.setOwnerUuid(entity.getOwner().getUuid());
        Set<String> setOfTagName = entity.getTags()
                .stream()
                .map(x -> x.getName())
                .collect(Collectors.toSet());
        itemModel.setTags(setOfTagName);
        itemModel.add(getDefaultItemLinks(itemModel));
        return itemModel;
    }


    public List<Link> getDefaultItemLinks(ItemModel model) {
        return List.of(
                linkTo(methodOn(ItemController.class).getItemById(model.getId())).withSelfRel()
                // linkTo(methodOn(ItemController.class).getAllItems(new PageImpl<Item>())).withRel("items")
        );
    }

}
