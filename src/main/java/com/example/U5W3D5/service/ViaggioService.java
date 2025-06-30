package com.example.U5W3D5.service;

import com.example.U5W3D5.dto.ViaggioDTO;
import com.example.U5W3D5.exception.NotFoundException;
import com.example.U5W3D5.model.StatoViaggio;
import com.example.U5W3D5.model.Viaggio;
import com.example.U5W3D5.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;

    public Viaggio saveViaggio(ViaggioDTO dto){
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(dto.getDestinazione());
        viaggio.setData(dto.getData());
        return viaggioRepository.save(viaggio);
    }
    public List<Viaggio> getAll(){
        return viaggioRepository.findAll();
    }

    public Viaggio getById(Long id)throws NotFoundException {
        return viaggioRepository.findById(id).orElseThrow(()->
                new NotFoundException("Viaggio con Id" + id + "non trovato"));
    }

    public Viaggio updateViaggio(Long id, ViaggioDTO dto) throws NotFoundException {
        Viaggio viaggio = getById(id);
        viaggio.setDestinazione(dto.getDestinazione());
        viaggio.setData(dto.getData());
        return viaggioRepository.save(viaggio);


    }

    public void delete(Long id)throws NotFoundException {
        Viaggio viaggio= getById(id);
        viaggioRepository.delete(viaggio);
    }

    public Viaggio cambiaStato(Long id, String nuovoStato) throws NotFoundException {
        Viaggio viaggio = getById(id);
        try {
            StatoViaggio stato = StatoViaggio.valueOf(nuovoStato.toUpperCase());
            viaggio.setStato(stato);
            return viaggioRepository.save(viaggio);
        } catch (IllegalArgumentException e){
            throw new NotFoundException("Stato non valido");
        }

    }
}
