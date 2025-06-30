package com.example.U5W3D5.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.U5W3D5.dto.DipendenteDTO;
import com.example.U5W3D5.exception.NotFoundException;
import com.example.U5W3D5.model.Dipendente;
import com.example.U5W3D5.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Dipendente saveDipendente(DipendenteDTO dto){
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(dto.getUsername());
        dipendente.setNome(dto.getNome());
        dipendente.setCognome(dto.getCognome());
        dipendente.setEmail(dto.getEmail());

        return dipendenteRepository.save(dipendente);
    }

    public List<Dipendente> getAll(){
        return dipendenteRepository.findAll();
    }
    public Dipendente getById(Long id) throws NotFoundException {
        return dipendenteRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Dipendente con ID" + id + "non trovato"));

    }
    public Dipendente update(Long id, DipendenteDTO dto) throws NotFoundException {
        Dipendente dipendente = getById(id);
        dipendente.setUsername(dto.getUsername());
        dipendente.setNome(dto.getNome());
        dipendente.setCognome(dto.getCognome());
        dipendente.setEmail(dto.getEmail());
        return dipendenteRepository.save(dipendente);
    }

    public void delete(Long id) throws NotFoundException {
        Dipendente dipendente = getById(id);
        dipendenteRepository.delete(dipendente);
    }

    public String uploadImmagine(Long id, MultipartFile file) throws NotFoundException, IOException {
        Dipendente dipendente = getById(id);

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = uploadResult.get("url").toString();

        dipendente.setImmagineProfilo(url);
        dipendenteRepository.save(dipendente);

        return url;
}
}
