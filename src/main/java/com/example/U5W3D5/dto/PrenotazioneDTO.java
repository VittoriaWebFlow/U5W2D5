package com.example.U5W3D5.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDTO {
    @NotEmpty(message = "La destinazione dev'essere obbligatoria")
    private String dipendenteId;
    @NotNull(message = "l'id deve esserci")
    private Long viaggioId;
    @NotNull(message = "la data deve essere obbligatoria")
    private LocalDate dataRichiesta;

    private String note;
}
