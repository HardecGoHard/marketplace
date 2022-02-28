package com.marketplace.marketplace.bootstrap;

import com.marketplace.marketplace.entity.*;
import com.marketplace.marketplace.service.ItemService;
import com.marketplace.marketplace.service.TagService;
import com.marketplace.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class BootstrapData implements CommandLineRunner {

    private final ItemService itemService;
    private final UserService userService;
    private final TagService tagService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BootstrapData(ItemService itemService, UserService userService, TagService tagService, PasswordEncoder passwordEncoder) {
        this.itemService = itemService;
        this.userService = userService;
        this.tagService = tagService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {

        //user
        User owner = new User();
        owner.setStatus(Status.ACTIVE);
        owner.setUsername("dizing");
        owner.setPassword(passwordEncoder.encode("trueHacker"));
        owner.setRole(Role.USER);
        owner.setRefreshCode("112");
        userService.save(owner);

        //first tag
        Tag tag = new Tag();
        tag.setName("Classical music");

        tagService.save(tag);

        //second tag
        Tag tag2 = new Tag();
        tag2.setName("huys");
        tagService.save(tag2);


        //item
        Item item = new Item();
        item.setOwner(owner);
        item.setName("Chlen");
        item.setDescription("V zhope");
        item.setTags(new HashSet<>());

        item.getTags().add(tagService.getByName("Classical music"));
        item.getTags().add(tag2);

        itemService.save(item);

    }
}
