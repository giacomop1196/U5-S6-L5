package com.giacomopillitteri.giacomopillitteri.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.giacomopillitteri.giacomopillitteri.entity.Dipendente;
import com.giacomopillitteri.giacomopillitteri.repository.DipendenteRepository;
import com.giacomopillitteri.giacomopillitteri.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;
import java.util.Map;

@Service
public class DipendenteService implements UserDetailsService {

    @Autowired private DipendenteRepository dipendenteRepo;
    @Autowired private Cloudinary cloudinary;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return dipendenteRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Dipendente non trovato con email: " + email));
    }

    public UserDetails loadUserById(UUID id) {
        return findById(id);
    }

    // CREATE (POST)
    public Dipendente save(Dipendente dipendente) {
        dipendente.setPassword(passwordEncoder.encode(dipendente.getPassword()));
        return dipendenteRepo.save(dipendente);
    }

    // READ ALL con Paginazione (GET)
    public Page<Dipendente> findAll(Pageable pageable) {
        return dipendenteRepo.findAll(pageable);
    }

    // READ BY ID (GET)
    public Dipendente findById(UUID id) {
        return dipendenteRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // UPDATE (PUT)
    public Dipendente update(UUID id, Dipendente updatedDipendente) {
        Dipendente existingDipendente = findById(id);

        existingDipendente.setUsername(updatedDipendente.getUsername());
        existingDipendente.setNome(updatedDipendente.getNome());
        existingDipendente.setCognome(updatedDipendente.getCognome());
        existingDipendente.setEmail(updatedDipendente.getEmail());

        return dipendenteRepo.save(existingDipendente);
    }

    // DELETE (DELETE)
    public void deleteById(UUID id) {
        if (!dipendenteRepo.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        dipendenteRepo.deleteById(id);
    }

    // UPLOAD (PATCH)
    public Dipendente uploadImmagineProfilo(UUID id, MultipartFile file) throws IOException {
        Dipendente dipendente = findById(id);

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = (String) uploadResult.get("url");

        dipendente.setImmagineProfilo(url);

        return dipendenteRepo.save(dipendente);
    }
}