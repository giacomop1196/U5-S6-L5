package com.giacomopillitteri.giacomopillitteri.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(UUID id) {
        super("Risorsa con ID " + id + " non trovata.");
    }
}