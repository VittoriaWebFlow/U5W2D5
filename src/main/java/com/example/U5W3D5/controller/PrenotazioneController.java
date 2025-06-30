package com.example.U5W3D5.controller;


import com.example.U5W3D5.dto.PrenotazioneDTO;
import com.example.U5W3D5.exception.NotFoundException;
import com.example.U5W3D5.model.Prenotazione;
import com.example.U5W3D5.service.PrenotazioneService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione create(@RequestBody @Valid PrenotazioneDTO dto, BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors())
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage()).reduce("", String::concat));

        return service.creaPrenotazione(dto);
    }

    @GetMapping
    public List<Prenotazione> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Prenotazione getById(@PathVariable Long id) throws NotFoundException {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NotFoundException {
        service.delete(id);
    }
}
