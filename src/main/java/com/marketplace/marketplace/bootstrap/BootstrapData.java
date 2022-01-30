package com.marketplace.marketplace.bootstrap;

import com.marketplace.marketplace.entity.*;
import com.marketplace.marketplace.repository.RoleRepository;
import com.marketplace.marketplace.service.ItemService;
import com.marketplace.marketplace.service.TagService;
import com.marketplace.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootstrapData implements CommandLineRunner {

    private final ItemService itemService;
    private final UserService userService;
    private final TagService tagService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public BootstrapData(
            ItemService itemService,
            UserService userService,
            TagService tagService,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            RoleRepository roleRepository) {
        this.itemService = itemService;
        this.userService = userService;
        this.tagService = tagService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Role role = new Role().setRole("USER");
        roleRepository.save(role);

        //user
        User owner = new User();
        owner.setStatus(Status.ACTIVE);
        owner.setUsername("owner");
        owner.setPassword(bCryptPasswordEncoder.encode("password"));
        owner.setRoles(Set.of(role));

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
