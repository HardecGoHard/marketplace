package com.marketplace.marketplace.controller;

import com.marketplace.marketplace.exception.ItemNotFoundException;
import com.marketplace.marketplace.exception.RestErrorMessage;
import com.marketplace.marketplace.exception.TagNotFoundException;
import com.marketplace.marketplace.exception.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@RestControllerAdvice
public class RestAdviceController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            EmptyResultDataAccessException.class,
            EntityNotFoundException.class,
            ItemNotFoundException.class,
            TagNotFoundException.class,
            UserNotFoundException.class
    })
    public <E extends RuntimeException> RestErrorMessage notFound(E ex) {
        RestErrorMessage message = new RestErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage()
        );

        return message;
    }
}
