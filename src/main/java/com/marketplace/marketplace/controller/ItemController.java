package com.marketplace.marketplace.controller;

import com.marketplace.marketplace.assembler.ItemModelAssembler;
import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.model.ItemModel;
import com.marketplace.marketplace.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("item")
public class ItemController {
    private final ItemService itemService;
    private final ItemModelAssembler itemModelAssembler;
    private final PagedResourcesAssembler<Item> pagedResourcesAssembler;


    @Autowired
    public ItemController(
            ItemService itemService,
            ItemModelAssembler itemModelAssembler,
            PagedResourcesAssembler<Item> pagedResourcesAssembler
    ) {
        this.itemService = itemService;
        this.itemModelAssembler = itemModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping()
    public PagedModel<ItemModel> getAllItems(@PageableDefault Pageable pageable) {
        return pagedResourcesAssembler.toModel(itemService.getAllPageble(pageable), itemModelAssembler);
    }

    @GetMapping("{id}")
    public ItemModel getItemById(@PathVariable Long id) {
        return itemModelAssembler.toModel(itemService.findById(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ItemModel createNew(
            @Validated @RequestBody ItemModel itemModel
    ) {

        return itemModelAssembler.toModel(
                itemService.saveModel(itemModel)
        );
    }
}
