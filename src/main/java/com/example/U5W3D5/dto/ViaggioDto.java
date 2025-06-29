package com.example.U5W3D5.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViaggioDto {
    @NotEmpty(message = "La destinazione dev'essere scelta")
    private String destinazione;
    @NotNull(message = "La data dev'essere obbligatoria")
    private LocalDate data;
}
