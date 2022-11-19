package com.marketplace.marketplace.assembler;

import com.marketplace.marketplace.dto.CommentDto;
import com.marketplace.marketplace.entity.Comment;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class CommentsDtoModelAssembler extends BaseModelAssembler<Comment, CommentDto> {
    private final UserDtoAssembler userDtoAssembler;

    public CommentsDtoModelAssembler(UserDtoAssembler userDtoAssembler) {
        super(CommentDto.class);
        this.userDtoAssembler = userDtoAssembler;
    }

    @Override
    public CommentDto toModel(Comment entity) {
        CommentDto commentDto = super.getEntityWithId(entity);
        commentDto
                    .setAuthor(userDtoAssembler.toModel(entity.getAuthor()))
                    .setText(entity.getText())
                    .setDate(entity.getCreatedAt().atZone(ZoneId.of("UTC")));

        return commentDto;
    }
}
