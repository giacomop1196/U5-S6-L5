package com.giacomopillitteri.giacomopillitteri.controller;

import com.giacomopillitteri.giacomopillitteri.dto.LoginDTO;
import com.giacomopillitteri.giacomopillitteri.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtService jwtService;

    // Login
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> login(@RequestBody @Valid LoginDTO loginDto) {
        // Esegue l'autenticazione usando email e password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        // Se l'autenticazione ha successo, genera il token
        String token = jwtService.generateToken(authentication);

        // Ritorna il token
        return Map.of("token", token);
    }
}