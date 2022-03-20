package com.marketplace.marketplace.assembler;

import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.model.ItemModel;
import org.springframework.stereotype.Component;

import java.util.HashSet;
//TODO make item model return TAGS not just as names, but as TagModel
@Component
public class ItemModelAssembler extends BaseModelAssembler<Item, ItemModel> {

    protected ItemModelAssembler() {
        super(ItemModel.class);
    }

    @Override
    public ItemModel toModel(Item entity) {
        ItemModel itemModel = this.getEntityWithId(entity);

        itemModel.setDescription(entity.getDescription());
        itemModel.setName(entity.getName());
        itemModel.setOwnerUuid(entity.getOwner().getUuid());
        HashSet<String> setOfTagName = new HashSet<>();
        entity.getTags().forEach(x -> setOfTagName.add(x.getName()));
        itemModel.setTags(setOfTagName);

        return itemModel;
    }

}
