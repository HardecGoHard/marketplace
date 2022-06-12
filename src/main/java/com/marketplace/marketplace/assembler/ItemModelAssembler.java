package com.marketplace.marketplace.assembler;

import com.marketplace.marketplace.controller.ItemController;
import com.marketplace.marketplace.dto.ItemDto;
import com.marketplace.marketplace.dto.TagDto;
import com.marketplace.marketplace.entity.Item;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class ItemModelAssembler extends BaseModelAssembler<Item, ItemDto> {

    private final TagModelAssembler tagModelAssembler;

    protected ItemModelAssembler(TagModelAssembler tagModelAssembler) {
        super(ItemDto.class);
        this.tagModelAssembler = tagModelAssembler;
    }

    @Override
    public ItemDto toModel(Item entity) {
        ItemDto itemDto = super.getEntityWithId(entity);

        itemDto
                .setDescription(entity.getDescription())
                .setName(entity.getName())
                .setOwnerId(entity.getOwner().getId())
                .setPrice(entity.getPrice())
                .setAvatar(Objects.isNull(entity.getAvatar())? "avatar.jpg" : entity.getAvatar());

        if (Objects.nonNull(entity.getTags())) {
            Set<TagDto> setOfTagName = entity.getTags()
                    .stream()
                    .map(x -> tagModelAssembler.toModel(x))
                    .collect(Collectors.toSet());
            itemDto.setTags(setOfTagName);
        }

        itemDto.add(linkTo(methodOn(ItemController.class).getItemById(entity.getId())).withSelfRel());
        return itemDto;
    }


}
