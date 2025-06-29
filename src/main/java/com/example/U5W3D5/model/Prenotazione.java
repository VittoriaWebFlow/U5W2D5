package com.example.U5W3D5.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Dipendente dipendente;

    @ManyToOne
    private Viaggio viaggio;

    private LocalDate dataRichiesta;

    private String note;

}
