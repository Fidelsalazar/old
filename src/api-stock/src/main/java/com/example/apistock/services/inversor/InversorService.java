package com.example.apistock.services.inversor;

import com.example.apistock.models.dto.AireVentanaDto;
import com.example.apistock.models.dto.InversorDto;

import java.util.List;
import java.util.UUID;

public interface InversorService {
  InversorDto create(
    InversorDto inversorDto
  ) throws Exception;

  InversorDto modified(
    UUID id, InversorDto inversorDto
  ) throws Exception;

  List<InversorDto> getAllInversor() throws  Exception;

  InversorDto getInversorForId(UUID id) throws Exception;
}
