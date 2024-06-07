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
public class PresurizadorDto {
  
  private UUID id;
  private String numActivo;
  private String fabricante;
  private String model;
  private String country;
  private Integer capacidad;
  private String dateInstalacion;
  private String tecnicalStatus;
  private Integer priorityLevel;
  private  Center center;

  public PresurizadorDto(String numActivo, String model) {
  }
}
