package com.giacomopillitteri.giacomopillitteri.service;

import com.giacomopillitteri.giacomopillitteri.entity.Dipendente;
import com.giacomopillitteri.giacomopillitteri.entity.Viaggio;
import com.giacomopillitteri.giacomopillitteri.entity.Prenotazione;
import com.giacomopillitteri.giacomopillitteri.dto.PrenotazionePayloadDTO;
import com.giacomopillitteri.giacomopillitteri.repository.PrenotazioneRepository;
import com.giacomopillitteri.giacomopillitteri.exception.DataOccupataException;
import com.giacomopillitteri.giacomopillitteri.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class PrenotazioneService {

    @Autowired private PrenotazioneRepository prenotazioneRepo;
    @Autowired private DipendenteService dipendenteService;
    @Autowired private ViaggioService viaggioService;

    // CREATE (POST) con Logica di Business
    public Prenotazione creaPrenotazione(PrenotazionePayloadDTO payload) {
        Dipendente dipendente = dipendenteService.findById(payload.getDipendenteId());
        Viaggio viaggio = viaggioService.findById(payload.getViaggioId());

        // VALIDAZIONE: Dipendente già impegnato per la data del viaggio.
        if (prenotazioneRepo.existsByDipendenteIdAndViaggio_Data(dipendente.getId(), viaggio.getData())) {
            throw new DataOccupataException("Il dipendente ha già una prenotazione per la data del viaggio: " + viaggio.getData());
        }

        Prenotazione nuovaPrenotazione = new Prenotazione();
        nuovaPrenotazione.setDipendente(dipendente);
        nuovaPrenotazione.setViaggio(viaggio);
        nuovaPrenotazione.setNote(payload.getNote());

        return prenotazioneRepo.save(nuovaPrenotazione);
    }

    // READ ALL con Paginazione (GET)
    public Page<Prenotazione> findAll(Pageable pageable) {
        return prenotazioneRepo.findAll(pageable);
    }

    // READ BY ID (GET)
    public Prenotazione findById(UUID id) {
        return prenotazioneRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // UPDATE (PUT) - Mantiene la logica di business sulla prenotazione
    public Prenotazione update(UUID id, PrenotazionePayloadDTO payload) {
        Prenotazione existingPrenotazione = findById(id);

        Dipendente newDipendente = dipendenteService.findById(payload.getDipendenteId());
        Viaggio newViaggio = viaggioService.findById(payload.getViaggioId());

        existingPrenotazione.setDipendente(newDipendente);
        existingPrenotazione.setViaggio(newViaggio);
        existingPrenotazione.setNote(payload.getNote());

        return prenotazioneRepo.save(existingPrenotazione);
    }

    // DELETE (DELETE)
    public void deleteById(UUID id) {
        if (!prenotazioneRepo.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        prenotazioneRepo.deleteById(id);
    }
}