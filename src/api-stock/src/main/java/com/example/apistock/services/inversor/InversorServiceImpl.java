package com.example.apistock.services.inversor;

import com.example.apistock.exceptions.SearchException;
import com.example.apistock.models.dto.InversorDto;
import com.example.apistock.models.entities.Inversor;
import com.example.apistock.repositories.InversorRepository;
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
public class InversorServiceImpl implements InversorService{

  @Autowired
  private final InversorRepository inversorRepository;

  public InversorServiceImpl(InversorRepository inversorRepository) {
    this.inversorRepository = inversorRepository;
  }

  @Override
  public InversorDto create(
    InversorDto inversorDto
  ) throws Exception{
    try{
      if(
        inversorRepository.findByNumActivo(inversorDto.getNumActivo()) != null
      ) {
        throw new SearchException("Resource already exists", HttpStatus.FOUND);
      }

      Inversor inversor = Inversor.builder()
        .numActivo(inversorDto.getNumActivo())
        .marca(inversorDto.getMarca())
        .model(inversorDto.getModel())
        .country(inversorDto.getCountry())
        .cantModules(inversorDto.getCantModules())
        .DC(inversorDto.getDC())
        .AC(inversorDto.getAC())
        .VA(inversorDto.getVA())
        .dateInstalation(inversorDto.getDateInstalation())
        .tecnicalStatus(inversorDto.getTecnicalStatus())
        .priorityLevel(inversorDto.getPriorityLevel())
        .center(inversorDto.getCenter())
        .build();

      inversorRepository.save(inversor);

      log.info("Create successful for ......." +
        inversorRepository.findByNumActivo(inversorDto.getNumActivo()).getNumActivo()+
        "with ID" + inversorRepository.findByNumActivo(inversorDto.getNumActivo()).getId()
      );

      InversorDto inversorDto1 = new InversorDto(inversorDto.getNumActivo(), inversorDto.getMarca(), inversorDto.getModel());
      return inversorDto1;
    }catch(Exception e) {
      log.error(e.getMessage());
      throw new Exception();
    }
  }

  @Override
  public InversorDto modified(
    UUID id, InversorDto inversorDto
  ) throws Exception {
    try {
      Optional<Inversor> existingInversorOptional = inversorRepository.findById(id);

      if(
        existingInversorOptional.isPresent()
      ) {

        Inversor existingAireVentana = getInversor(inversorDto, existingInversorOptional);

        log.info("Modified successful for ......." +
          existingAireVentana.getNumActivo() +
          " with ID " + existingAireVentana.getId()
        );

        return new InversorDto(inversorDto.getNumActivo(), inversorDto.getMarca(), inversorDto.getModel());
      }else{
        log.error("Modified error for ......." + inversorDto.getNumActivo());
      }
    }catch (Exception e) {
      log.error("An error occurred while modified the center ", e);
      throw new Exception("Error modifying center", e);
    }
    return null;
  }

  @Override
  public List<InversorDto> getAllInversor() throws  Exception{
    try{
      List <InversorDto> inversorDtoList = new ArrayList<>();

      inversorRepository.findAll().forEach( i -> {
        InversorDto inversorDto = InversorDto.builder()
          .id(i.getId())
          .numActivo(i.getNumActivo())
          .marca(i.getMarca())
          .model(i.getModel())
          .country(i.getCountry())
          .cantModules(i.getCantModules())
          .DC(i.getDC())
          .AC(i.getAC())
          .VA(i.getVA())
          .dateInstalation(i.getDateInstalation())
          .tecnicalStatus(i.getTecnicalStatus())
          .priorityLevel(i.getPriorityLevel())
          .principalUsage(i.getPrincipalUsage())
          .build();

        inversorDtoList.add(inversorDto);
      });

      return inversorDtoList;
    }catch (Exception e) {
      log.error(e.getMessage());
      throw new Exception(e);
    }
  }

  @Override
  public InversorDto getInversorForId(UUID id) throws Exception{
    try{
      return InversorDto.builder()
        .numActivo(inversorRepository.findById(id).get().getNumActivo())
        .marca(inversorRepository.findById(id).get().getMarca())
        .model(inversorRepository.findById(id).get().getModel())
        .country(inversorRepository.findById(id).get().getCountry())
        .DC(inversorRepository.findById(id).get().getDC())
        .AC(inversorRepository.findById(id).get().getAC())
        .VA(inversorRepository.findById(id).get().getVA())
        .dateInstalation(inversorRepository.findById(id).get().getDateInstalation())
        .tecnicalStatus(inversorRepository.findById(id).get().getTecnicalStatus())
        .priorityLevel(inversorRepository.findById(id).get().getPriorityLevel())
        .principalUsage(inversorRepository.findById(id).get().getPrincipalUsage())
        .build();
    }catch (Exception e) {
      log.error(e.getMessage());
      throw new Exception();
    }
  }

  private static Inversor getInversor(InversorDto inversorDto, Optional<Inversor> existingInversorOptional) {
    Inversor existingInversor = existingInversorOptional.get();

    existingInversor.setNumActivo(inversorDto.getNumActivo());
    existingInversor.setPrincipalUsage(inversorDto.getPrincipalUsage());
    existingInversor.setMarca(inversorDto.getMarca());
    existingInversor.setModel(inversorDto.getModel());
    existingInversor.setCountry(inversorDto.getCountry());
    existingInversor.setCantModules(inversorDto.getCantModules());
    existingInversor.setDC(inversorDto.getDC());
    existingInversor.setAC(inversorDto.getAC());
    existingInversor.setVA(inversorDto.getVA());
    existingInversor.setDateInstalation(inversorDto.getDateInstalation());
    existingInversor.setTecnicalStatus(inversorDto.getTecnicalStatus());
    existingInversor.setPriorityLevel(inversorDto.getPriorityLevel());

    return existingInversor;
  }
}
