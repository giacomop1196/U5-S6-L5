package com.giacomopillitteri.giacomopillitteri.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@Data
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate dataRichiesta = LocalDate.now();
    private String note;

    // Una prenotazione ha un solo Dipendente
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    // Una prenotazione ha un solo Viaggio
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;
}