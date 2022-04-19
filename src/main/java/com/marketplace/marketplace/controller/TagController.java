package com.marketplace.marketplace.controller;

import com.marketplace.marketplace.assembler.TagModelAssembler;
import com.marketplace.marketplace.entity.Tag;
import com.marketplace.marketplace.model.TagModel;
import com.marketplace.marketplace.service.TagService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tag")
public class TagController {
    private TagService tagService;
    private TagModelAssembler tagModelAssembler;

    private final PagedResourcesAssembler<Tag> pagedResourcesAssembler;


    public TagController(
            TagService tagService,
            TagModelAssembler tagModelAssembler,
            PagedResourcesAssembler<Tag> pagedResourcesAssembler
    ) {
        this.tagService = tagService;
        this.tagModelAssembler = tagModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("{id}")
    public TagModel getItemById(@PathVariable Long id) {
        return tagModelAssembler.toModel(tagService.findById(id));
    }

    @PostMapping()
    public TagModel createNew(@RequestBody @Validated TagModel tagModel) {
        return tagModelAssembler.toModel(tagService.buildPersistTagEntityByName(tagModel.getName()));
    }

    @GetMapping()
    public PagedModel<TagModel> getAllTags(@PageableDefault Pageable pageable) {
        return pagedResourcesAssembler.toModel(tagService.findAllPageble(pageable), tagModelAssembler);
    }

    @DeleteMapping("{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteById(id);
    }
}
