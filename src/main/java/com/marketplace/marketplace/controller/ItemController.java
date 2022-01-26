package com.marketplace.marketplace.controller;

import com.marketplace.marketplace.assembler.ItemModelAssembler;
import com.marketplace.marketplace.model.ItemModel;
import com.marketplace.marketplace.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item")
public class ItemController {
    private final ItemService itemService;
    private final ItemModelAssembler itemModelAssembler;

    @Autowired
    public ItemController(
            ItemService itemService,
            ItemModelAssembler itemModelAssembler
    ) {
        this.itemService = itemService;
        this.itemModelAssembler = itemModelAssembler;
    }

    @GetMapping("{id}")
    public ItemModel getItemById(@PathVariable Long id) {
        return itemModelAssembler.toModel(
                itemService.getById(id)
        );
    }

    @GetMapping("new")
    public ItemModel createNew() {
        return new ItemModel();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("new")
    public ItemModel createNew(
            @Validated @RequestBody ItemModel itemModel
    ) {
        //itemService.create(ItemModel);
        return itemModel;
    }
}
