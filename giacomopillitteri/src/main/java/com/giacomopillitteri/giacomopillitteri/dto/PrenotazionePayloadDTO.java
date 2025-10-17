package com.giacomopillitteri.giacomopillitteri.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.UUID;

@Data
public class PrenotazionePayloadDTO {

    @NotNull(message = "L'ID del dipendente è obbligatorio")
    private UUID dipendenteId;

    @NotNull(message = "L'ID del viaggio è obbligatorio")
    private UUID viaggioId;

    private String note; // Campo opzionale
}