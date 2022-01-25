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

        Tag huys = new Tag();
        User owner = new User();
        Item huy = new Item();

        huys.setName("huys");
        huys.setItems(new HashSet<Item>());


        huy.setName("Chlen");
        huy.setDescription("V zhope");
        huy.setOwner(owner);
        huy.setTags(new HashSet<Tag>());

        huys.getItems().add(huy);
        huy.getTags().add(huys);

        owner.setEmail("huesos@chleni.ru");
        owner.setName("Ruslan");
        owner.setOwnedItems(new HashSet<Item>());
        owner.getOwnedItems().add(huy);
        owner.setPassword("oralCumshot");
        owner.setSurname("aloev");

        itemService.save(huy);
        userService.save(owner);
        tagService.save(huys);


    }
}
