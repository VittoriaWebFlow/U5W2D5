package com.example.U5W3D5.repository;

import com.example.U5W3D5.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteIdAndDataRichiesta(Long dipendenteId, LocalDate dataRichiesta);
}
