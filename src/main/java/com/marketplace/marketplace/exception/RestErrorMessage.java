package com.marketplace.marketplace.exception;

import java.util.Date;

public class RestErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;

    public RestErrorMessage(int statusCode, Date timestamp, String message) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
    }
}
