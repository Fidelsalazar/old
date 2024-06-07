package com.example.apistock.services.aireVentana;

import com.example.apistock.exceptions.SearchException;
import com.example.apistock.models.dto.AireVentanaDto;
import com.example.apistock.models.entities.AireVentana;
import com.example.apistock.repositories.AireVentanaRepository;
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
public class AireVentanaServiceImpl implements AireVentanaService{

    @Autowired
    private final AireVentanaRepository aireVentanaRepository;

    public AireVentanaServiceImpl(AireVentanaRepository aireVentanaRepository) {
        this.aireVentanaRepository = aireVentanaRepository;
    }

    @Override
    public AireVentanaDto create(
      AireVentanaDto aireVentanaDto
    ) throws Exception {
      try{
        if (
          aireVentanaRepository.findByNumActivo(aireVentanaDto.getNumActivo()) != null
        ) {
          throw new SearchException("Resource already exists", HttpStatus.FOUND);
        }
        AireVentana aire = AireVentana.builder()
            .numActivo(aireVentanaDto.getNumActivo())
            .local(aireVentanaDto.getLocal())
            .category(aireVentanaDto.getCategory())
            .marca(aireVentanaDto.getMarca())
            .model(aireVentanaDto.getModel())
            .country(aireVentanaDto.getCountry())
            .V(aireVentanaDto.getV())
            .refrigerante(aireVentanaDto.getRefrigerante())
            .capacidad(aireVentanaDto.getCapacidad())
            .dateInstalacion(aireVentanaDto.getDateInstalacion())
            .tecnicalStatus(aireVentanaDto.getTecnicalStatus())
            .levelPriority(aireVentanaDto.getLevelPriority())
            .center(aireVentanaDto.getCenter())
            .build();

        aireVentanaRepository.save(aire);

        log.info("Create successful for ......." +
          aireVentanaRepository.findByNumActivo(aireVentanaDto.getNumActivo()).getNumActivo()+
          "with ID" + aireVentanaRepository.findByNumActivo(aireVentanaDto.getNumActivo()).getId()
        );

        AireVentanaDto aire1 = new AireVentanaDto(aireVentanaDto.getNumActivo(), aireVentanaDto.getLocal());
        return aire1;
      }catch(Exception e) {
        log.error(e.getMessage());
        throw new Exception();
      }
    }

    @Override
    public AireVentanaDto modified(
      UUID id, AireVentanaDto aireVentanaDto
    ) throws Exception {
      try {
        Optional <AireVentana> existingAireVentanaOptional = aireVentanaRepository.findById(id);

        if(
          existingAireVentanaOptional.isPresent()
        ) {

          AireVentana existingAireVentana = getAireVentana(aireVentanaDto, existingAireVentanaOptional);

          log.info("Modified successful for ......." +
            existingAireVentana.getNumActivo() +
            " with ID " + existingAireVentana.getId()
          );

          return new AireVentanaDto(aireVentanaDto.getNumActivo(), aireVentanaDto.getLocal());
        }else{
          log.error("Modified error for ......." + aireVentanaDto.getNumActivo());
        }
      }catch (Exception e) {
        log.error("An error occurred while modified the center ", e);
        throw new Exception("Error modifying center", e);
      }
      return null;
    }

  @Override
    public List<AireVentanaDto> getAllAireVentana() throws  Exception{
      try{
        List <AireVentanaDto> aireVentanaDtoList = new ArrayList<>();

        aireVentanaRepository.findAll().forEach( i -> {
          AireVentanaDto aireVentanaDto = AireVentanaDto.builder()
            .id(i.getId())
            .numActivo(i.getNumActivo())
            .local(i.getLocal())
            .category(i.getCategory())
            .marca(i.getMarca())
            .model(i.getModel())
            .country(i.getCountry())
            .V(i.getV())
            .refrigerante(i.getRefrigerante())
            .capacidad(i.getCapacidad())
            .dateInstalacion(i.getDateInstalacion())
            .tecnicalStatus(i.getTecnicalStatus())
            .levelPriority(i.getLevelPriority())
            .center(i.getCenter())
            .build();

          aireVentanaDtoList.add(aireVentanaDto);
        });

        return aireVentanaDtoList;
      }catch (Exception e) {
        log.error(e.getMessage());
        throw new Exception(e);
      }
    }

    @Override
    public AireVentanaDto getAireVentanaForId(UUID id) throws Exception{
      try{
        return AireVentanaDto.builder()
          .numActivo(aireVentanaRepository.findById(id).get().getNumActivo())
          .local(aireVentanaRepository.findById(id).get().getLocal())
          .category(aireVentanaRepository.findById(id).get().getCategory())
          .marca(aireVentanaRepository.findById(id).get().getMarca())
          .model(aireVentanaRepository.findById(id).get().getModel())
          .country(aireVentanaRepository.findById(id).get().getCountry())
          .V(aireVentanaRepository.findById(id).get().getV())
          .refrigerante(aireVentanaRepository.findById(id).get().getRefrigerante())
          .capacidad(aireVentanaRepository.findById(id).get().getCapacidad())
          .dateInstalacion(aireVentanaRepository.findById(id).get().getDateInstalacion())
          .tecnicalStatus(aireVentanaRepository.findById(id).get().getTecnicalStatus())
          .levelPriority(aireVentanaRepository.findById(id).get().getLevelPriority())
          .build();
      }catch (Exception e) {
        log.error(e.getMessage());
        throw new Exception();
      }
    }

  @Override
  public String delete(UUID id) throws Exception {
    try{
      aireVentanaRepository.deleteById(id);
      return null ;
    }catch(Exception e) {
      log.error(e.getMessage());
      throw new Exception();
    }

  }

  private static AireVentana getAireVentana(AireVentanaDto aireVentanaDto, Optional<AireVentana> existingAireVentanaOptional) {
    AireVentana existingAireVentana = existingAireVentanaOptional.get();

    existingAireVentana.setNumActivo(aireVentanaDto.getNumActivo());
    existingAireVentana.setLocal(aireVentanaDto.getLocal());
    existingAireVentana.setCategory(aireVentanaDto.getCategory());
    existingAireVentana.setMarca(aireVentanaDto.getMarca());
    existingAireVentana.setModel(aireVentanaDto.getModel());
    existingAireVentana.setCountry(aireVentanaDto.getCountry());
    existingAireVentana.setV(aireVentanaDto.getV());
    existingAireVentana.setRefrigerante(aireVentanaDto.getRefrigerante());
    existingAireVentana.setCapacidad(aireVentanaDto.getCapacidad());
    existingAireVentana.setDateInstalacion(aireVentanaDto.getDateInstalacion());
    existingAireVentana.setTecnicalStatus(aireVentanaDto.getTecnicalStatus());
    existingAireVentana.setLevelPriority(aireVentanaDto.getLevelPriority());

    return existingAireVentana;
  }
}
