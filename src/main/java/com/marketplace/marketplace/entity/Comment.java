package com.marketplace.marketplace.entity;

import com.marketplace.marketplace.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Comment extends BaseEntity {

    @Column(name = "author_id", insertable = false, updatable = false)
    private Long authorId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @Column(name = "item_id", insertable = false, updatable = false )
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


    private String text;

    public Long getAuthorId() {
        return authorId;
    }

    public Comment setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Comment setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getText() {
        return text;
    }

    public Comment setText(String text) {
        this.text = text;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public Comment setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public Comment setItem(Item item) {
        this.item = item;
        return this;
    }

}
