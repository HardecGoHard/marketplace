package com.marketplace.marketplace.service;

import com.marketplace.marketplace.dto.CommentDto;
import com.marketplace.marketplace.entity.Comment;
import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.exception.entity.ItemNotFoundException;
import com.marketplace.marketplace.repository.CommentRepository;
import com.marketplace.marketplace.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends AbstractService<Comment, Long> {
    private final CommentRepository commentRepository;
    private final ItemService itemService;
    private final UserService userService;


    @Autowired
    public CommentService(
            CommentRepository commentRepository,
            ItemService itemService,
            UserService userService
    ) {
        super(commentRepository);
        this.commentRepository = commentRepository;
        this.itemService = itemService;
        this.userService = userService;
    }

    public Page<Comment> findAllPageable(Pageable pageable, Long itemId) {
        if (itemService.existsById(itemId))
            return commentRepository.findAllByItemId(itemId, pageable);
        else
            throw new ItemNotFoundException("Item not found");
    }

    public List<Comment> findAllByItemId(Long itemId) {
        if (itemService.existsById(itemId))
            return commentRepository.findAllByItemId(itemId);
        else
            throw new ItemNotFoundException("Item not found");
    }
    public Comment addNewComment(CommentDto commentDto, Long itemId, UserPrincipal authUser) {
        Comment comment = new Comment();
        User author = userService.findById(authUser.getId());
        Item item = itemService.findById(itemId);

        comment
                .setAuthor(author)
                .setAuthorId(author.getId())
                .setItem(item)
                .setItemId(item.getId())
                .setText(commentDto.getText());

        return save(comment);

    }
}
