package com.example.apistock.services.aireVentana;

import com.example.apistock.models.dto.AireVentanaDto;


import java.util.List;
import java.util.UUID;

public interface AireVentanaService {
  AireVentanaDto create (AireVentanaDto aireVentanaDto) throws Exception;

  AireVentanaDto modified(UUID id, AireVentanaDto aireVentanaDto) throws Exception;

  List<AireVentanaDto> getAllAireVentana() throws  Exception;

  AireVentanaDto getAireVentanaForId(UUID id) throws Exception;

    String delete(UUID id) throws Exception;
}
