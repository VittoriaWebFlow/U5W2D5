package com.example.U5W3D5.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApiError {
    private String message;
    private LocalDate dataErrore;
}
