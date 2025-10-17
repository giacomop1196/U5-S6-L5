package com.giacomopillitteri.giacomopillitteri.service;

import com.giacomopillitteri.giacomopillitteri.entity.Viaggio;
import com.giacomopillitteri.giacomopillitteri.entity.StatoViaggio;
import com.giacomopillitteri.giacomopillitteri.dto.ViaggioUpdateStatoDTO;
import com.giacomopillitteri.giacomopillitteri.repository.ViaggioRepository;
import com.giacomopillitteri.giacomopillitteri.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepo;

    // CREATE (POST)
    public Viaggio save(Viaggio viaggio) {
        return viaggioRepo.save(viaggio);
    }

    // READ ALL con Paginazione (GET)
    public Page<Viaggio> findAll(Pageable pageable) {
        return viaggioRepo.findAll(pageable);
    }

    // READ BY ID (GET)
    public Viaggio findById(UUID id) {
        return viaggioRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // UPDATE (PUT)
    public Viaggio update(UUID id, Viaggio updatedViaggio) {
        Viaggio existingViaggio = findById(id);

        existingViaggio.setDestinazione(updatedViaggio.getDestinazione());
        existingViaggio.setData(updatedViaggio.getData());
        existingViaggio.setStato(updatedViaggio.getStato());

        return viaggioRepo.save(existingViaggio);
    }

    // DELETE (DELETE)
    public void deleteById(UUID id) {
        if (!viaggioRepo.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        viaggioRepo.deleteById(id);
    }

    // UPDATE STATO
    public Viaggio updateStato(UUID id, ViaggioUpdateStatoDTO payload) {
        Viaggio viaggio = findById(id);
        StatoViaggio nuovoStato = StatoViaggio.valueOf(payload.getNuovoStato().toUpperCase());
        viaggio.setStato(nuovoStato);
        return viaggioRepo.save(viaggio);
    }
}