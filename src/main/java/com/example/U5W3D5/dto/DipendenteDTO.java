package com.example.U5W3D5.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DipendenteDTO {
@NotEmpty(message = "l'username non può essere vuoto o nullo")
    private String username;
@NotEmpty(message = "Il nome non può essere vuoto")
    private String nome;
    @NotEmpty(message = "Il cognome non può essere vuoto")
    private String cognome;
    @Email(message = "l'email non può essere vuota")
    private String email;

}
