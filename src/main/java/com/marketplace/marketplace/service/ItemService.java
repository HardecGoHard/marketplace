package com.marketplace.marketplace.service;

import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.exception.ItemNotFoundException;
import com.marketplace.marketplace.model.ItemModel;
import com.marketplace.marketplace.repository.ItemRepository;
import com.marketplace.marketplace.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final UsersRepository usersRepository;
    private final TagService tagService;

    @Autowired
    public ItemService(ItemRepository itemRepository, UsersRepository usersRepository, TagService tagService) {
        this.itemRepository = itemRepository;
        this.usersRepository = usersRepository;
        this.tagService = tagService;
    }

    public void save(Item item) {
        itemRepository.save(item);
    }

    public void delete(Item item) {
        itemRepository.delete(item);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public boolean existsById(Long id) {
        return itemRepository.existsById(id);
    }

    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Item by id= %d not found", id)));
    }

    public Item getByUuid(String uuid) {
        return itemRepository.findByUuid(uuid)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Item by uuid= %s not found", uuid)));
    }

    private Item buildItemEntityFromModel(ItemModel model){
        Item item = new Item();

        item.setName(model.getName());
        item.setDescription(model.getDescription());
        item.setOwner(usersRepository.findByUuid(model.getOwnerUuid()).orElse(null));
        item.setTags(tagService.buildTagsByNames(model.getTags()));

        return item;
    }

    private boolean modelExists(ItemModel model){
        return itemRepository.existsById(model.getId());
    }

    private Item saveModel(ItemModel model){
        Item item = buildItemEntityFromModel(model);
        itemRepository.save(item);
        return item;
    }




}
