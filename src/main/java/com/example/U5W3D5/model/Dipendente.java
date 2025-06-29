package com.example.U5W3D5.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String nome;
    private String cognome;
    private String email;

    private String immagineProfilo;
}
