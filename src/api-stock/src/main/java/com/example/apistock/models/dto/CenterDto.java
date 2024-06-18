package com.example.apistock.models.dto;


import com.example.apistock.models.entities.*;
import com.example.apistock.models.entities.grupoelectrogeno.grupo;
import com.example.apistock.models.entities.rectifcadores.SistemaRectificador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CenterDto {
  private  UUID id;
  private String center;
  private String sitio;

  private List<Split> splits;
  private List<Inversor> inversors;
  private List<PanelSolar> panelSolars;
  private List<Presurizador> presurizadors;
  private List<AireVentana> aireVentanas;
  private List<SistemaRectificador> sistemaRectificadors;
  private List<grupo> grupos;

  public CenterDto(String center, String sitio) {
    this.center = center;
    this.sitio = sitio;
  }
}
