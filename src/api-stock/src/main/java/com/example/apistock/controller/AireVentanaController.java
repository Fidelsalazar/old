package com.example.apistock.controller;

import com.example.apistock.models.dto.AireVentanaDto;
import com.example.apistock.services.aireVentana.AireVentanaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("v1/api/aireVentana")
public class AireVentanaController {

  @Autowired
  private final AireVentanaService aireVentanaService;

  public AireVentanaController(AireVentanaService aireVentanaService) {
    this.aireVentanaService = aireVentanaService;
  }

  @PostMapping
  public ResponseEntity<AireVentanaDto> createAireVentana(
    @RequestBody AireVentanaDto aireVentanaDto
  ) throws Exception{
    log.info(String.valueOf(aireVentanaDto));
    return new ResponseEntity<>(
      aireVentanaService.create( aireVentanaDto ),
      HttpStatus.OK
    );
  }

  @PatchMapping("/{id}")
  public  ResponseEntity<AireVentanaDto> modify(
    @PathVariable UUID id,
    @RequestBody AireVentanaDto update
  ) throws Exception {
    return new ResponseEntity<>(
      aireVentanaService.modified(id, update),
      HttpStatus.OK
    );
  }

  @GetMapping("/all")
  public ResponseEntity<List<AireVentanaDto>> getAllAireVentana() throws  Exception {
    return new ResponseEntity<>(
      aireVentanaService.getAllAireVentana(),
      HttpStatus.OK
    );
  }

  @GetMapping("/getbyid/{id}")
  public ResponseEntity<AireVentanaDto> getById (
    @PathVariable UUID id
  ) throws Exception {
    return new ResponseEntity<>(
      aireVentanaService.getAireVentanaForId(id),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(
    @PathVariable UUID id
  )throws Exception{
    return  new ResponseEntity<>(
      aireVentanaService.delete(id),
      HttpStatus.OK
    );
  }
}
