package com.astontech.rest.controllers.advice;

import com.astontech.rest.exceptions.FieldNotFoundException;
import com.astontech.rest.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFoundHandler(ProductNotFoundException pEx) {
        return pEx.getLocalizedMessage();
    }

    @ResponseBody
    @ExceptionHandler(FieldNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String fieldNotFoundHandler(FieldNotFoundException fEx) {
        return fEx.getLocalizedMessage();
    }

    @ExceptionHandler(Exception.class)
    String generalExceptionHandler(Exception ex) {
        return "General Server Error";
    }
}
