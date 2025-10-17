package com.giacomopillitteri.giacomopillitteri.repository;

import com.giacomopillitteri.giacomopillitteri.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {

    // Verificare se esiste già una prenotazione per quel dipendente in quella specifica data del viaggio
    boolean existsByDipendenteIdAndViaggio_Data(UUID dipendenteId, LocalDate dataViaggio);
}