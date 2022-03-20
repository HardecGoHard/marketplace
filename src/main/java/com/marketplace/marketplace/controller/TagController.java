package com.marketplace.marketplace.controller;

import com.marketplace.marketplace.assembler.TagModelAssembler;
import com.marketplace.marketplace.entity.Tag;
import com.marketplace.marketplace.model.ItemModel;
import com.marketplace.marketplace.model.TagModel;
import com.marketplace.marketplace.service.TagService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tag")
public class TagController {
    private TagService tagService;
    private TagModelAssembler tagModelAssembler;

    public TagController(TagService tagService, TagModelAssembler tagModelAssembler) {
        this.tagService = tagService;
        this.tagModelAssembler = tagModelAssembler;
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
    public List<TagModel> getAllTags() {
        return tagService.getAll().stream()
                .map(entity -> tagModelAssembler.toModel(entity))
                .collect(Collectors.toList());
    }

    @DeleteMapping("{id}")
    public List<TagModel> deleteTag(@PathVariable Long id) {
        tagService.delete(tagService.findById(id));
        return getAllTags();
    }
}
