package com.example.U5W3D5.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity

public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destinazione;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatoViaggio stato = StatoViaggio.IN_PROGRAMMA;
}
