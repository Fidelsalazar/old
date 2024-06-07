package com.example.apistock.repositories;

import com.example.apistock.models.entities.Inversor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InversorRepository extends JpaRepository<Inversor, UUID> {

  Inversor findByNumActivo(String numActivo);
}
