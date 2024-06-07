package com.example.apistock.models.dto;

import com.example.apistock.models.entities.Center;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InversorDto {

  private UUID id;
  private String principalUsage;
  private String numActivo;
  private String marca;
  private String model;
  private String country;
  private String cantModules;
  private Double DC;
  private Double AC;
  private Double VA;
  private String dateInstalation;
  private String tecnicalStatus;
  private Integer priorityLevel;
  private  Center center;

  public InversorDto(String numActivo, String marca, String model) {
  }
}
