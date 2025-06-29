package com.example.U5W3D5.service;

import com.example.U5W3D5.model.Prenotazione;
import com.example.U5W3D5.repository.DipendenteRepository;
import com.example.U5W3D5.repository.PrenotazioneRepository;
import com.example.U5W3D5.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private ViaggioRepository viaggioRepository;

    public Prenotazione creaPrenotazione(Long dipId, Long viaggioId, LocalDate data, String note){
        if(prenotazioneRepository.existsByDipendenteIdAndDataRichiesta(dipId, data)){
            throw new IllegalStateException("Già prenotato per questo giorno");
        }
    }
}
