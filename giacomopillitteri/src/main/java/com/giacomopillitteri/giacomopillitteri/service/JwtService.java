package com.giacomopillitteri.giacomopillitteri.service;

import com.giacomopillitteri.giacomopillitteri.entity.Dipendente;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${jwt.secret:A_SECRET_KEY_WITH_AT_LEAST_256_BITS_FOR_HS256_ALGORITHM_MUST_BE_HERE}")
    private String secret;

    @Value("${jwt.expiration.millis:3600000}") // 1 ora
    private long expirationMillis;

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Creazione del Token
    public String generateToken(Authentication authentication) {
        Dipendente user = (Dipendente) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    // Estrazione dell'ID Utente dal Token
    public UUID extractUserId(String token) {
        String userIdStr = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return UUID.fromString(userIdStr);
    }

    // Validazione del Token
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {

            return false;
        }
    }
}