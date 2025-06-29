package com.example.U5W3D5.exception;

import com.example.U5W3D5.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomizedExceptionHandler {

    @ExceptionHandler(NotFounException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(NotFounException e ){
        ApiError error = new ApiError();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDate.now());
        return error;
    }

    public ApiError handleValidation(ValidationException e ){
        ApiError error= new ApiError();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDate.now());
        return error;
    }
}
