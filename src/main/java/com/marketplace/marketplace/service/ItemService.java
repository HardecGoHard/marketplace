package com.marketplace.marketplace.service;

import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.exception.entity.ItemNotFoundException;
import com.marketplace.marketplace.model.ItemModel;
import com.marketplace.marketplace.repository.ItemRepository;
import com.marketplace.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//TODO make service place the owner by the security context, not by the passed id
@Service
public class ItemService extends AbstractService<Item, Long> {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final TagService tagService;

    @Autowired
    public ItemService(
            ItemRepository itemRepository,
            UserRepository userRepository,
            TagService tagService
    ) {
        super(itemRepository, ItemNotFoundException::new);
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.tagService = tagService;
    }

    public Item getByUuid(String uuid) {
        return itemRepository.findByUuid(uuid)
                                    .orElseThrow(ItemNotFoundException::new);
    }

    public boolean modelExists(ItemModel model) {
        return existsById(model.getId());
    }

    public Item saveModel(ItemModel model) {
        Item item = buildItemEntityFromModel(model);
        return save(item);
    }

    private Item buildItemEntityFromModel(ItemModel model) {
        Item item = new Item();

        item.setName(model.getName());
        item.setDescription(model.getDescription());
        item.setOwner(userRepository.findByUuid(model.getOwnerUuid()).orElse(null));
        item.setTags(tagService.getPersistTagsByNames(model.getTags()));

        return item;
    }
}
