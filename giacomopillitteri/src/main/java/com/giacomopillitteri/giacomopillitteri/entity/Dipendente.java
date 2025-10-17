package com.giacomopillitteri.giacomopillitteri.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "dipendenti")
@Data
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String immagineProfilo; // URL dell'immagine caricata su Cloudinary
}