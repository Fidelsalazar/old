package com.example.apistock.controller;

import com.example.apistock.models.dto.AireVentanaDto;
import com.example.apistock.models.dto.InversorDto;
import com.example.apistock.services.inversor.InversorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("v1/api/inversor")
public class InversorController {

  @Autowired
  private final InversorService inversorService;

  public InversorController(InversorService inversorService) {
    this.inversorService = inversorService;
  }

  @PostMapping
  public ResponseEntity<InversorDto> createAireVentana(
    @RequestBody InversorDto inversorDto
  ) throws Exception{
    log.info(String.valueOf(inversorDto));
    return new ResponseEntity<>(
      inversorService.create( inversorDto ),
      HttpStatus.OK
    );
  }

  @PatchMapping("/{id}")
  public  ResponseEntity<InversorDto> modify(
    @PathVariable UUID id,
    @RequestBody InversorDto update
  ) throws Exception {
    return new ResponseEntity<>(
      inversorService.modified(id, update),
      HttpStatus.OK
    );
  }

  @GetMapping("/all")
  public ResponseEntity<List<InversorDto>> getAllAireVentana() throws  Exception {
    return new ResponseEntity<>(
      inversorService.getAllInversor(),
      HttpStatus.OK
    );
  }

  @GetMapping("/getbyid/{id}")
  public ResponseEntity<InversorDto> getById (
    @PathVariable UUID id
  ) throws Exception {
    return new ResponseEntity<>(
      inversorService.getInversorForId(id),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(
    @PathVariable UUID id
  )throws Exception{
    return  new ResponseEntity<>(
      inversorService.delete(id),
      HttpStatus.OK
    );
  }
}
