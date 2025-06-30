package com.example.U5W3D5.controller;


import com.example.U5W3D5.dto.ViaggioDTO;
import com.example.U5W3D5.exception.NotFoundException;
import com.example.U5W3D5.model.Viaggio;
import com.example.U5W3D5.service.ViaggioService;
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
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio create(@RequestBody @Valid ViaggioDTO dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult.getAllErrors().stream().
                map(e -> e.getDefaultMessage()).reduce("", String::concat));
        return service.saveViaggio(dto);
    }

    @GetMapping
    public List<Viaggio> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Viaggio getById(@PathVariable Long id)throws NotFoundException {
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public Viaggio updateViaggio(@PathVariable Long id, @RequestBody @Valid ViaggioDTO dto, BindingResult bindingResult) throws NotFoundException {
        if(bindingResult.hasErrors())throw new ValidationException(bindingResult.getAllErrors().stream().
                map(e-> e.getDefaultMessage()).reduce("",String::concat));
        return service.updateViaggio(id, dto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NotFoundException {
        service.delete(id);
    }
    @PatchMapping("/{id}/stato")
    public Viaggio cambiaStato(@PathVariable Long id, @RequestParam String stato) throws NotFoundException {
        return service.cambiaStato(id, stato);
    }
    @PostMapping("/{id}/foto")
    public String uploadFoto(@PathVariable Long id, @RequestParam("file") MultipartFile file)
            throws IOException, NotFoundException {
        return service.uploadFoto(id, file);
    }
}
