package com.example.apistock.models.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AireVentanaDto {

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
}
