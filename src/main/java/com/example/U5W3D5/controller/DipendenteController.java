package com.example.U5W3D5.controller;

import com.example.U5W3D5.dto.DipendenteDTO;
import com.example.U5W3D5.exception.NotFoundException;
import com.example.U5W3D5.model.Dipendente;
import com.example.U5W3D5.service.DipendenteService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente create(@RequestBody @Valid DipendenteDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage()).reduce("", String::concat));

        return service.saveDipendente(dto);
    }

    @GetMapping
    public List<Dipendente> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Dipendente getOne(@PathVariable Long id) throws NotFoundException {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Dipendente update(@PathVariable Long id, @RequestBody @Valid DipendenteDTO dto, BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors())
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage()).reduce("", String::concat));

        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NotFoundException {
        service.delete(id);
    }

    @PatchMapping("/{id}/immagine")
    public String uploadImmagine(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws NotFoundException, IOException {
        return service.uploadImmagine(id, file);
    }
}
