package com.marketplace.marketplace.service;

import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.repository.CommentRepository;
import com.marketplace.marketplace.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemDeleteService {
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;

    public ItemDeleteService(
            ItemRepository itemRepository,
            CommentRepository commentRepository
    ) {
        this.itemRepository = itemRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void deleteItem(Item item) {
        commentRepository.deleteAllByItem(item);
        itemRepository.delete(item);
    }
}
