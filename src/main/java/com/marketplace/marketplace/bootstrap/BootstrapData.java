package com.marketplace.marketplace.bootstrap;

import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.entity.Tag;
import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.service.ItemService;
import com.marketplace.marketplace.service.TagService;
import com.marketplace.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootstrapData implements CommandLineRunner {

    private final ItemService itemService;
    private final UserService userService;
    private final TagService tagService;

    @Autowired
    public BootstrapData(ItemService itemService, UserService userService, TagService tagService) {
        this.itemService = itemService;
        this.userService = userService;
        this.tagService = tagService;
    }


    @Override
    public void run(String... args) throws Exception {

        User owner = new User();
        userService.save(owner);

        Item item = new Item();
        item.setOwner(owner);
        item.setName("Chlen");
        item.setDescription("V zhope");

        Tag tag = new Tag();
        tag.setName("huys");
        tag.setItems(Set.of(item));
        item.setTags(Set.of(tag));
        itemService.save(item);


    }
}
