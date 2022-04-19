package com.marketplace.marketplace.exception.entity;

public class TagNotFoundException extends RuntimeException{
    public TagNotFoundException(String message) {
        super(message);
    }

    public TagNotFoundException() {
        super();
    }
}
