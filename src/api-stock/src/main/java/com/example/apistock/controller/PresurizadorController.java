package com.example.apistock.controller;

import com.example.apistock.models.dto.AireVentanaDto;
import com.example.apistock.models.dto.PresurizadorDto;
import com.example.apistock.services.presurizador.PresurizadorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("v1/api/presurizador")
public class PresurizadorController {

  @Autowired
  private final PresurizadorService presurizadorService;

  public PresurizadorController(PresurizadorService presurizadorService) {
    this.presurizadorService = presurizadorService;
  }

  @PostMapping
  public ResponseEntity<PresurizadorDto> createAireVentana(
    @RequestBody PresurizadorDto presurizadorDto
  ) throws Exception{
    log.info(String.valueOf(presurizadorDto));
    return new ResponseEntity<>(
      presurizadorService.create( presurizadorDto ),
      HttpStatus.OK
    );
  }

  @PatchMapping("/{id}")
  public  ResponseEntity<PresurizadorDto> modify(
    @PathVariable UUID id,
    @RequestBody PresurizadorDto update
  ) throws Exception {
    return new ResponseEntity<>(
      presurizadorService.modified(id, update),
      HttpStatus.OK
    );
  }

  @GetMapping("/all")
  public ResponseEntity<List<PresurizadorDto>> getAllAireVentana() throws  Exception {
    return new ResponseEntity<>(
      presurizadorService.getAllPresurizador(),
      HttpStatus.OK
    );
  }

  @GetMapping("/getbyid/{id}")
  public ResponseEntity<PresurizadorDto> getById (
    @PathVariable UUID id
  ) throws Exception {
    return new ResponseEntity<>(
      presurizadorService.getPresurizadorForId(id),
      HttpStatus.OK
    );
  }
}
