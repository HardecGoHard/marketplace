package com.marketplace.marketplace.service;

import com.marketplace.marketplace.dto.from.ItemEditFormDto;
import com.marketplace.marketplace.dto.from.ItemFormDto;
import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.dto.ItemDto;
import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.repository.ItemRepository;
import com.marketplace.marketplace.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

//TODO make service place the owner by the security context, not by the passed id
@Service
public class ItemService extends AbstractService<Item, Long> {
    private final ItemRepository itemRepository;
    private final ImageService imageService;
    private final UserService userService;
    private final TagService tagService;


    @Autowired
    public ItemService(
            ItemRepository itemRepository,
            ImageService imageService,
            TagService tagService,
            UserService userService
    ) {
        super(itemRepository);
        this.itemRepository = itemRepository;
        this.imageService = imageService;
        this.userService = userService;
        this.tagService = tagService;
    }


    public boolean modelExists(ItemDto model) {
        return existsById(model.getId());
    }

    public Item save(ItemFormDto itemFormDto, UserPrincipal userPrincipal) {
        Item item = new Item();
        User owner = userService.findById(userPrincipal.getId());
        item
                .setPrice(itemFormDto.getPrice())
                .setOwner(owner)
                .setOwnerId(owner.getId())
                .setDescription(itemFormDto.getDescription())
                .setName(itemFormDto.getName())
                .setAvatar(imageService.saveImage(itemFormDto.getAvatar()));

        return save(item);
    }

    public Item update(ItemEditFormDto itemFormDto, Long itemId) {
        Item item = findById(itemId);
        item
                .setName(itemFormDto.getName())
                .setPrice(itemFormDto.getPrice())
                .setDescription(itemFormDto.getDescription());

        return save(item);
    }

    public Page<Item> getAllPageble(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }


    public void uploadAvatar(Long itemId, MultipartFile avatar) {
        Item item = findById(itemId);
        if (Objects.nonNull(item.getAvatar()) && !avatar.isEmpty()) {
            imageService.deleteFileImage(item.getAvatar());
        }
        item.setAvatar(imageService.saveImage(avatar));

        save(item);
    }

// private Item buildItemEntityFromDto(ItemFormDto itemFormDto, Item item) {
//
//     return item
//                 .setName(itemFormDto.getName())
//                 .setDescription(itemFormDto.getDescription())
//                 .setPrice(itemFormDto.getPrice());
//                 //.setOwner(userService.findById(itemFormDto.getOwnerId()))
//                 //.setTags(tagService.getPersistTagsByTagModelsSet(itemFormDto.getTags()));
//
//
// }
}
