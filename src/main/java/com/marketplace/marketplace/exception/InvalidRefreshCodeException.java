package com.marketplace.marketplace.exception;

public class InvalidRefreshCodeException extends RuntimeException {
    public InvalidRefreshCodeException(String message) {
        super(message);
    }
}