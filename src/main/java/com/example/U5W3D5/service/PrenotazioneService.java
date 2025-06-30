package com.example.U5W3D5.service;

import com.example.U5W3D5.dto.PrenotazioneDTO;
import com.example.U5W3D5.exception.NotFoundException;
import com.example.U5W3D5.model.Dipendente;
import com.example.U5W3D5.model.Prenotazione;
import com.example.U5W3D5.model.Viaggio;
import com.example.U5W3D5.repository.DipendenteRepository;
import com.example.U5W3D5.repository.PrenotazioneRepository;
import com.example.U5W3D5.repository.ViaggioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    public Prenotazione creaPrenotazione(PrenotazioneDTO dto) throws NotFoundException {
        // Validazione: doppia prenotazione stesso giorno
        if (prenotazioneRepository.existsByDipendenteIdAndDataRichiesta(dto.getDipendenteId(), dto.getDataRichiesta())) {
            throw new ValidationException("Dipendente già prenotato per la data " + dto.getDataRichiesta());
        }

        Dipendente dipendente = dipendenteRepository.findById(dto.getDipendenteId()).orElseThrow(() ->
                new NotFoundException("Dipendente non trovato"));

        Viaggio viaggio = viaggioRepository.findById(dto.getViaggioId()).orElseThrow(() ->
                new NotFoundException("Viaggio non trovato"));

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataRichiesta(dto.getDataRichiesta());
        prenotazione.setNote(dto.getNote());

        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> getAll() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione getById(Long id) throws NotFoundException {
        return prenotazioneRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Prenotazione non trovata"));
    }

    public void delete(Long id) throws NotFoundException {
        Prenotazione p = getById(id);
        prenotazioneRepository.delete(p);
    }
}
