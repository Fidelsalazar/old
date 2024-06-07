package com.example.apistock.services.presurizador;

import com.example.apistock.exceptions.SearchException;
import com.example.apistock.models.dto.PresurizadorDto;
import com.example.apistock.models.entities.Presurizador;
import com.example.apistock.repositories.PresurizadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class PresurizadorServiceImpl implements PresurizadorService{

  @Autowired
  private final PresurizadorRepository presurizadorRepository;

  Presurizador presurizador = new Presurizador();
  PresurizadorDto presurizadorDto = new PresurizadorDto();

  public PresurizadorServiceImpl(PresurizadorRepository presurizadorRepository, ModelMapper modelMapper) {
    this.presurizadorRepository = presurizadorRepository;
  }

  @Override
  public PresurizadorDto create(
    PresurizadorDto presurizadorDto
  ) throws Exception {
    try{
      if (
        presurizadorRepository.findByNumActivo(presurizadorDto.getNumActivo()) != null
      ) {
        throw new SearchException("Resource already exists", HttpStatus.FOUND);
      }

      Presurizador presurizador = Presurizador.builder()
        .numActivo(presurizadorDto.getNumActivo())
        .fabricante(presurizadorDto.getFabricante())
        .model(presurizadorDto.getModel())
        .country(presurizadorDto.getCountry())
        .capacidad(presurizadorDto.getCapacidad())
        .dateInstalacion(presurizadorDto.getDateInstalacion())
        .tecnicalStatus(presurizadorDto.getTecnicalStatus())
        .priorityLevel(presurizadorDto.getPriorityLevel())
        .center(presurizadorDto.getCenter())
        .build();

      presurizadorRepository.save(presurizador);

      log.info("Create successful for ......." +
        presurizadorRepository.findByNumActivo(presurizadorDto.getNumActivo()).getNumActivo()+
        "with ID" + presurizadorRepository.findByNumActivo(presurizadorDto.getNumActivo()).getId()
      );

      PresurizadorDto presurizadorDto1 = new PresurizadorDto(presurizadorDto.getNumActivo(), presurizadorDto.getModel());
      return presurizadorDto1;
    }catch(Exception e) {
      log.error(e.getMessage());
      throw new Exception();
    }
  }

  @Override
  public PresurizadorDto modified(
    UUID id, PresurizadorDto presurizadorDto
  ) throws Exception {
    try {
      Optional<Presurizador> existingPresurizadorOptional = presurizadorRepository.findById(id);

      if(
        existingPresurizadorOptional.isPresent()
      ) {

        BeanUtils.copyProperties(presurizadorDto, presurizador);

        presurizadorRepository.save(presurizador);

        log.info("Modified successful for ......." +
          presurizador.getNumActivo() +
          " with ID " + presurizador.getId()
        );

        return new PresurizadorDto(presurizadorDto.getNumActivo(), presurizadorDto.getModel() );
      }else{
        log.error("Modified error for ......." + presurizadorDto.getNumActivo());
      }
    }catch (Exception e) {
      log.error("An error occurred while modified the center ", e);
      throw new Exception("Error modifying center", e);
    }
    return null;
  }

  @Override
  public List<PresurizadorDto> getAllPresurizador() throws Exception {
    try{
      List<PresurizadorDto> presurizadorDtoList = new ArrayList<>();

      presurizadorRepository.findAll().forEach( i ->{
        BeanUtils.copyProperties( presurizador, presurizadorDto);
        presurizadorDtoList.add(presurizadorDto);
      });

      return  presurizadorDtoList;
    }catch (Exception e) {
      log.error(e.getMessage());
      throw new Exception(e);
    }
  }

  @Override
  public PresurizadorDto getPresurizadorForId( UUID id ) throws Exception {
    try{
      BeanUtils.copyProperties(
        presurizadorRepository.findById(id),
        presurizadorDto
      );
      return presurizadorDto;
    }catch (Exception e) {
      log.error(e.getMessage());
      throw new Exception(e);
    }
  }
}
