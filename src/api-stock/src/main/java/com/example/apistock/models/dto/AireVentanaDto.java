package com.example.apistock.models.dto;

import com.example.apistock.models.entities.Center;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AireVentanaDto {

    private UUID id;
    private String numActivo;
    private String local;
    private String category;
    private String marca;
    private String model;
    private String country;
    private Integer V;
    private String refrigerante;
    private String capacidad;
    private String dateInstalacion;
    private String tecnicalStatus;
    private Integer levelPriority;
    private Center center;

  public AireVentanaDto(String numActivo, String local) {
  }
}
