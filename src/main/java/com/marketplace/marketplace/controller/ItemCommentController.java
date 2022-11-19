package com.marketplace.marketplace.controller;

import com.marketplace.marketplace.assembler.CommentsDtoModelAssembler;
import com.marketplace.marketplace.dto.CommentDto;
import com.marketplace.marketplace.entity.Comment;
import com.marketplace.marketplace.security.UserPrincipal;
import com.marketplace.marketplace.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item/{itemId}/comment")
public class ItemCommentController {
    private final CommentsDtoModelAssembler commentsDtoModelAssembler;
    private final CommentService commentService;

    @Autowired
    public ItemCommentController(
            CommentsDtoModelAssembler commentsDtoModelAssembler,
            CommentService commentService
    ) {
        this.commentsDtoModelAssembler = commentsDtoModelAssembler;
        this.commentService = commentService;
    }

    @GetMapping()
    public CollectionModel<CommentDto> getComments(
            @PathVariable("itemId") Long itemId
    ) {
        return commentsDtoModelAssembler.toCollectionModel(
                commentService.findAllByItemId(itemId)
        );
    }

    @PostMapping()
    public CommentDto newComment(
            @PathVariable("itemId") Long itemId,
            @RequestBody CommentDto commentDto,
            @AuthenticationPrincipal UserPrincipal authUser
    ) {
        return commentsDtoModelAssembler.toModel(
                commentService.addNewComment(commentDto, itemId, authUser)
        );
    }
}
