package com.example.apistock.repositories;

import com.example.apistock.models.dto.PresurizadorDto;
import com.example.apistock.models.entities.Inversor;
import com.example.apistock.models.entities.Presurizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PresurizadorRepository extends JpaRepository<Presurizador, UUID> {

  PresurizadorDto findByNumActivo(String numActivo);
}
