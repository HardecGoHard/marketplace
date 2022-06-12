package com.marketplace.marketplace.controller;

import com.marketplace.marketplace.assembler.TagModelAssembler;
import com.marketplace.marketplace.entity.Tag;
import com.marketplace.marketplace.dto.TagDto;
import com.marketplace.marketplace.service.TagService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public TagDto getItemById(@PathVariable Long id) {
        return tagModelAssembler.toModel(tagService.findById(id));
    }

    @PostMapping()
    public TagDto createNew(@RequestBody @Validated TagDto tagDto) {
        return tagModelAssembler.toModel(tagService.buildPersistTagEntityByName(tagDto.getName()));
    }

    @GetMapping()
    public PagedModel<TagDto> getAllTags(@PageableDefault Pageable pageable) {
        return pagedResourcesAssembler.toModel(tagService.findAllPageble(pageable), tagModelAssembler);
    }

    @DeleteMapping("{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteById(id);
    }
}
