package com.example.apistock.services.panelSolar;

import com.example.apistock.models.dto.PanelSolarDto;

import java.util.List;
import java.util.UUID;

public interface PanelSolarService {
  PanelSolarDto create (PanelSolarDto panelSolarDto) throws Exception;

  PanelSolarDto modified(UUID id, PanelSolarDto panelSolarDto) throws Exception;

  List<PanelSolarDto> getAllPanelSolar() throws  Exception;

  PanelSolarDto getPanelSolarForId(UUID id) throws Exception;
}
