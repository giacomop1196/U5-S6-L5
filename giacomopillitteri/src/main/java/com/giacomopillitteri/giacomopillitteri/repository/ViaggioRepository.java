package com.giacomopillitteri.giacomopillitteri.repository;

import com.giacomopillitteri.giacomopillitteri.entity.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, UUID> {

}