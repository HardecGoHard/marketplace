package com.marketplace.marketplace.exception;

import java.util.Date;

public class RestErrorMessage {
    private final int statusCode;
    private final Date timestamp;
    private final String message;

    public RestErrorMessage(int statusCode, Date timestamp, String message) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
    }
}
