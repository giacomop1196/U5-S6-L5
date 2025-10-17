package com.giacomopillitteri.giacomopillitteri.controller;

import com.giacomopillitteri.giacomopillitteri.entity.Prenotazione;
import com.giacomopillitteri.giacomopillitteri.dto.PrenotazionePayloadDTO;
import com.giacomopillitteri.giacomopillitteri.service.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired private PrenotazioneService prenotazioneService;

    // GET ALL (Con Paginazione e Ordinamento)
    @GetMapping
    public Page<Prenotazione> findAll(Pageable pageable) {
        return prenotazioneService.findAll(pageable);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Prenotazione findById(@PathVariable UUID id) {
        return prenotazioneService.findById(id);
    }

    // POST (Creazione)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione create(@RequestBody @Valid PrenotazionePayloadDTO payload) {
        return prenotazioneService.creaPrenotazione(payload);
    }

    // PUT (Modifica Completa)
    @PutMapping("/{id}")
    public Prenotazione update(@PathVariable UUID id, @RequestBody @Valid PrenotazionePayloadDTO payload) {
        return prenotazioneService.update(id, payload);
    }

    // DELETE (Cancellazione)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        prenotazioneService.deleteById(id);
    }
}