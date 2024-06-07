package com.example.apistock.repositories;

import com.example.apistock.models.entities.Inversor;
import com.example.apistock.models.entities.PanelSolar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PanelSolarRepository extends JpaRepository<PanelSolar, UUID> {

  PanelSolar findByNumActivo(String numActivo);
}
