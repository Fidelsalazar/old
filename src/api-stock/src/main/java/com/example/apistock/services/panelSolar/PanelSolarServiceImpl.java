package com.example.apistock.services.panelSolar;

import com.example.apistock.exceptions.SearchException;
import com.example.apistock.models.dto.PanelSolarDto;
import com.example.apistock.models.entities.PanelSolar;
import com.example.apistock.repositories.PanelSolarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class PanelSolarServiceImpl implements  PanelSolarService{

  @Autowired
  private final PanelSolarRepository panelSolarRepository;

  public PanelSolarServiceImpl(PanelSolarRepository panelSolarRepository) {
    this.panelSolarRepository = panelSolarRepository;
  }

  @Override
  public PanelSolarDto create(
    PanelSolarDto panelSolarDto
  ) throws Exception {
    try{
      if (
        panelSolarRepository.findByNumActivo(panelSolarDto.getNumActivo()) != null
      ) {
        throw new SearchException("Resource already exists", HttpStatus.FOUND);
      }
      PanelSolar panelSolar = PanelSolar.builder()
        .numActivo(panelSolarDto.getNumActivo())
        .marca(panelSolarDto.getMarca())
        .model(panelSolarDto.getModel())
        .cantPanel(panelSolarDto.getCantPanel())
        .capacity(panelSolarDto.getCapacity())
        .voltaje(panelSolarDto.getVoltaje())
        .date(panelSolarDto.getDate())
        .tecnicalStatus(panelSolarDto.getTecnicalStatus())
        .center(panelSolarDto.getCenter())
        .build();

      panelSolarRepository.save(panelSolar);

      log.info("Create successful for ......." +
        panelSolarRepository.findByNumActivo(panelSolarDto.getNumActivo()).getNumActivo()+
        "with ID" + panelSolarRepository.findByNumActivo(panelSolarDto.getNumActivo()).getId()
      );

      PanelSolarDto panelSolarDto1 = new PanelSolarDto(panelSolarDto.getNumActivo(), panelSolarDto.getMarca(), panelSolarDto.getModel());

      return panelSolarDto1;
    }catch(Exception e) {
      log.error(e.getMessage());
      throw new Exception();
    }
  }

  @Override
  public PanelSolarDto modified(
    UUID id, PanelSolarDto panelSolarDto
  ) throws Exception {
    try {
      Optional<PanelSolar> existingPanelSolarOptional = panelSolarRepository.findById(id);

      if(
        existingPanelSolarOptional.isPresent()
      ) {

        PanelSolar existingPanelSolar = getPanelSolar(panelSolarDto, existingPanelSolarOptional);

        log.info("Modified successful for ......." +
          existingPanelSolar.getNumActivo() +
          " with ID " + existingPanelSolar.getId()
        );

        return new PanelSolarDto(panelSolarDto.getNumActivo(), panelSolarDto.getMarca(), panelSolarDto.getModel());
      }else{
        log.error("Modified error for ......." + panelSolarDto.getNumActivo());
      }
    }catch (Exception e) {
      log.error("An error occurred while modified the center ", e);
      throw new Exception("Error modifying center", e);
    }
    return null;
  }

  @Override
  public List<PanelSolarDto> getAllPanelSolar() throws  Exception{
    try{
      List <PanelSolarDto> panelSolarDtoList = new ArrayList<>();

      panelSolarRepository.findAll().forEach( i -> {
        PanelSolarDto panelSolarDto = PanelSolarDto.builder()
          .id(i.getId())
          .numActivo(i.getNumActivo())
          .marca(i.getMarca())
          .model(i.getModel())
          .cantPanel(i.getCantPanel())
          .voltaje(i.getVoltaje())
          .date(i.getDate())
          .tecnicalStatus(i.getTecnicalStatus())
          .center(i.getCenter())
          .build();

        panelSolarDtoList.add(panelSolarDto);
      });

      return panelSolarDtoList;
    }catch (Exception e) {
      log.error(e.getMessage());
      throw new Exception(e);
    }
  }

  @Override
  public PanelSolarDto getPanelSolarForId(UUID id) throws Exception {
    try {
      return PanelSolarDto.builder()
        .numActivo(panelSolarRepository.findById(id).get().getNumActivo())
        .marca(panelSolarRepository.findById(id).get().getMarca())
        .model(panelSolarRepository.findById(id).get().getModel())
        .cantPanel(panelSolarRepository.findById(id).get().getCantPanel())
        .capacity(panelSolarRepository.findById(id).get().getCapacity())
        .voltaje(panelSolarRepository.findById(id).get().getVoltaje())
        .date(panelSolarRepository.findById(id).get().getDate())
        .tecnicalStatus(panelSolarRepository.findById(id).get().getTecnicalStatus())
        .center(panelSolarRepository.findById(id).get().getCenter())
        .build();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new Exception();
    }
  }

  private static PanelSolar getPanelSolar(PanelSolarDto panelSolarDto, Optional<PanelSolar> existingPanelSolarOptional) {
   PanelSolar existingPanelSolar = existingPanelSolarOptional.get();

    existingPanelSolar.setNumActivo(panelSolarDto.getNumActivo());
    existingPanelSolar.setMarca(panelSolarDto.getMarca());
    existingPanelSolar.setModel(panelSolarDto.getModel());
    existingPanelSolar.setCantPanel(panelSolarDto.getCantPanel());
    existingPanelSolar.setCapacity(panelSolarDto.getCapacity());
    existingPanelSolar.setVoltaje(panelSolarDto.getVoltaje());
    existingPanelSolar.setDate(panelSolarDto.getDate());
    existingPanelSolar.setTecnicalStatus(panelSolarDto.getTecnicalStatus());
    existingPanelSolar.setCenter(panelSolarDto.getCenter());

    return existingPanelSolar;
  }
}
