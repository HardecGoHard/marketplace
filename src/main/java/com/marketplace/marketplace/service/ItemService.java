package com.marketplace.marketplace.service;

import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.model.ItemModel;
import com.marketplace.marketplace.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//TODO make service place the owner by the security context, not by the passed id
@Service
public class ItemService extends AbstractService<Item, Long> {
    private final ItemRepository itemRepository;
    private final UserService userService;
    private final TagService tagService;

    @Autowired
    public ItemService(
            ItemRepository itemRepository,
            TagService tagService,
            UserService userService
    ) {
        super(itemRepository);
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.tagService = tagService;
    }


    public boolean modelExists(ItemModel model) {
        return existsById(model.getId());
    }

    public Item save(ItemModel model) {
        Item item = buildItemEntityFromModel(model, new Item());
        return save(item);
    }

    public Item update(ItemModel model) {
        Item item = findById(model.getId());
        return save(buildItemEntityFromModel(model, item));
    }

    public Page<Item> getAllPageble(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    private Item buildItemEntityFromModel(ItemModel model, Item item) {

        return item
                    .setName(model.getName())
                    .setDescription(model.getDescription())
                    .setOwner(userService.findById(model.getOwnerId()))
                    .setTags(tagService.getPersistTagsByTagModelsSet(model.getTags()));


    }
}
