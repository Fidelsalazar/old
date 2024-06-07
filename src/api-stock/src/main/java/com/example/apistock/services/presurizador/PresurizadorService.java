package com.example.apistock.services.presurizador;

import com.example.apistock.models.dto.AireVentanaDto;
import com.example.apistock.models.dto.PresurizadorDto;

import java.util.List;
import java.util.UUID;

public interface PresurizadorService {

  PresurizadorDto create (PresurizadorDto presurizadorDto) throws Exception;

  PresurizadorDto modified(UUID id, PresurizadorDto presurizadorDto) throws Exception;

  PresurizadorDto getPresurizadorForId(UUID id) throws Exception;

  List<PresurizadorDto> getAllPresurizador() throws Exception;
}
