package com.marketplace.marketplace.exception.entity;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException() {

    }

}
