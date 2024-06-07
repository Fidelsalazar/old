package com.example.apistock.models.dto;

import com.example.apistock.models.entities.Center;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PanelSolarDto {

  private UUID id;
  private String numActivo;
  private String marca;
  private String model;
  private Integer cantPanel;
  private Double capacity;
  private Double voltaje;
  private String date;
  private String tecnicalStatus;
  private  Center center;

  public PanelSolarDto(String numActivo, String marca, String model) {
  }
}
