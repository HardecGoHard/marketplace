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

    public int getStatusCode() {
        return statusCode;
    }

    public RestErrorMessage setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public RestErrorMessage setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestErrorMessage setMessage(String message) {
        this.message = message;
        return this;
    }
}
