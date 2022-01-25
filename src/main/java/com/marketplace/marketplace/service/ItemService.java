package com.marketplace.marketplace.service;

import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.exception.ItemNotFoundException;
import com.marketplace.marketplace.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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


}
