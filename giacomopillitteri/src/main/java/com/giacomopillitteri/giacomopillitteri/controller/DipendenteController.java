package com.giacomopillitteri.giacomopillitteri.controller;

import com.giacomopillitteri.giacomopillitteri.entity.Dipendente;
import com.giacomopillitteri.giacomopillitteri.service.DipendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired private DipendenteService dipendenteService;

    // GET ALL (Con Paginazione e Ordinamento)
    @GetMapping
    public Page<Dipendente> findAll(Pageable pageable) {
        return dipendenteService.findAll(pageable);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Dipendente findById(@PathVariable UUID id) {
        return dipendenteService.findById(id);
    }

    // POST (Creazione)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody @Valid Dipendente dipendente) {
        return dipendenteService.save(dipendente);
    }

    // PUT (Modifica)
    @PutMapping("/{id}")
    public Dipendente update(@PathVariable UUID id, @RequestBody @Valid Dipendente dipendente) {
        return dipendenteService.update(id, dipendente);
    }

    // DELETE (Cancellazione)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        dipendenteService.deleteById(id);
    }

    // PATCH (Upload Immagine Profilo)
    @PatchMapping("/{id}/profilo")
    public Dipendente uploadProfilo(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        try {
            return dipendenteService.uploadImmagineProfilo(id, file);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante l'upload del file: " + e.getMessage());
        }
    }
}