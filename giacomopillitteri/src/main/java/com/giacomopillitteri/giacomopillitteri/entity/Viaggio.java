package com.giacomopillitteri.giacomopillitteri.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "viaggi")
@Data
public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String destinazione;
    private LocalDate data; // Data di inizio del viaggio

    @Enumerated(EnumType.STRING)
    private StatoViaggio stato = StatoViaggio.IN_PROGRAMMA;
}