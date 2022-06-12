package com.marketplace.marketplace.controller;

import com.marketplace.marketplace.assembler.ItemModelAssembler;
import com.marketplace.marketplace.dto.ItemDto;
import com.marketplace.marketplace.dto.from.ItemEditFormDto;
import com.marketplace.marketplace.dto.from.ItemFormDto;
import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.security.UserPrincipal;
import com.marketplace.marketplace.service.ItemDeleteService;
import com.marketplace.marketplace.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("item")
public class ItemController {
    private final ItemService itemService;
    private final ItemModelAssembler itemModelAssembler;
    private final ItemDeleteService itemDeleteService;
    private final PagedResourcesAssembler<Item> pagedResourcesAssembler;


    @Autowired
    public ItemController(
            ItemService itemService,
            ItemModelAssembler itemModelAssembler,
            ItemDeleteService itemDeleteService,
            PagedResourcesAssembler<Item> pagedResourcesAssembler
    ) {
        this.itemService = itemService;
        this.itemModelAssembler = itemModelAssembler;
        this.itemDeleteService = itemDeleteService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping()
    public PagedModel<ItemDto> getAllItems(@PageableDefault Pageable pageable) {
        return pagedResourcesAssembler.toModel(itemService.getAllPageble(pageable), itemModelAssembler);
    }

    @GetMapping("{id}")
    public ItemDto getItemById(@PathVariable Long id) {
        return itemModelAssembler.toModel(itemService.findById(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ItemDto createNew(
            @ModelAttribute ItemFormDto itemDto,
            @AuthenticationPrincipal UserPrincipal authUser
    ) {

        return itemModelAssembler.toModel(
                itemService.save(itemDto, authUser)
        );
    }

    @PutMapping("{id}")
    public ItemDto update(
            @ModelAttribute ItemEditFormDto itemDto,
            @PathVariable("id") Long itemId
    ) {

        return itemModelAssembler.toModel(
                itemService.update(itemDto, itemId)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public void delete(
            @PathVariable("id") Long itemId
    ) {
        Item item = itemService.findById(itemId);
        itemDeleteService.deleteItem(item);
    }

    @PostMapping("{id}/avatar")
    public void uploadAvatar(
            @RequestParam("avatar") MultipartFile multipartFile,
            @PathVariable("id") Long itemId
    ) {
        itemService.uploadAvatar(itemId, multipartFile);
    }
}
