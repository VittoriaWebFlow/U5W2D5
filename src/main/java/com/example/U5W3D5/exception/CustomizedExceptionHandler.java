package com.example.U5W3D5.exception;

import com.example.U5W3D5.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(NotFoundException e ){
        ApiError error = new ApiError();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDate.now());
        return error;
    }

@ExceptionHandler(ValidationException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidation(ValidationException e ){
        ApiError error= new ApiError();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDate.now());
        return error;
    }
}
