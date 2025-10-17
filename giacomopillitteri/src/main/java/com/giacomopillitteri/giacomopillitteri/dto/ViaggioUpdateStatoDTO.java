package com.giacomopillitteri.giacomopillitteri.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ViaggioUpdateStatoDTO {

    @NotBlank(message = "Lo stato è obbligatorio")
    @Pattern(regexp = "IN_PROGRAMMA|COMPLETATO|CANCELLATO", message = "Stato non valido")
    private String nuovoStato;
}