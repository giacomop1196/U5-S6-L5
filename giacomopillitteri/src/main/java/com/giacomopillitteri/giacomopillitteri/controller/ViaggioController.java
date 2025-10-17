package com.giacomopillitteri.giacomopillitteri.controller;

import com.giacomopillitteri.giacomopillitteri.entity.Viaggio;
import com.giacomopillitteri.giacomopillitteri.dto.ViaggioUpdateStatoDTO;
import com.giacomopillitteri.giacomopillitteri.service.ViaggioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired private ViaggioService viaggioService;

    // GET ALL (Con Paginazione e Ordinamento)
    @GetMapping
    public Page<Viaggio> findAll(Pageable pageable) {
        return viaggioService.findAll(pageable);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Viaggio findById(@PathVariable UUID id) {
        return viaggioService.findById(id);
    }

    // POST (Creazione)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio save(@RequestBody @Valid Viaggio viaggio) {
        return viaggioService.save(viaggio);
    }

    // PUT (Modifica Completa)
    @PutMapping("/{id}")
    public Viaggio update(@PathVariable UUID id, @RequestBody @Valid Viaggio viaggio) {
        return viaggioService.update(id, viaggio);
    }

    // DELETE (Cancellazione)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        viaggioService.deleteById(id);
    }

    // PATCH (Modifica Stato)
    @PatchMapping("/{id}/stato")
    public Viaggio updateStato(@PathVariable UUID id, @RequestBody @Valid ViaggioUpdateStatoDTO payload) {
        return viaggioService.updateStato(id, payload);
    }
}