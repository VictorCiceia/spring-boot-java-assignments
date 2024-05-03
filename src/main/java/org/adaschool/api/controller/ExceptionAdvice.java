package org.adaschool.api.controller;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String productNotFoundException(final ProductNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundException(final UserNotFoundException e) {
        return e.getMessage();
    }

}
