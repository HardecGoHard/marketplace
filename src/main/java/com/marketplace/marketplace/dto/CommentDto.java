package com.marketplace.marketplace.dto;

import com.marketplace.marketplace.dto.base.BaseModel;

import java.time.ZonedDateTime;

public class CommentDto extends BaseModel<CommentDto> {
    private String text;
    private UserDto author;

    private ZonedDateTime date;

    public String getText() {
        return text;
    }

    public CommentDto setText(String text) {
        this.text = text;
        return this;
    }

    public UserDto getAuthor() {
        return author;
    }

    public CommentDto setAuthor(UserDto author) {
        this.author = author;
        return this;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public CommentDto setDate(ZonedDateTime date) {
        this.date = date;
        return this;
    }
}
