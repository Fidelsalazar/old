package com.example.apistock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apistock.models.entities.AireVentana;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AireVentanaRepository extends JpaRepository<AireVentana, UUID> {

  AireVentana findByNumActivo(String numActivo);
}
