package com.example.apistock.controller;

import com.example.apistock.models.dto.AireVentanaDto;
import com.example.apistock.models.dto.PanelSolarDto;
import com.example.apistock.services.panelSolar.PanelSolarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("v1/api/panelSolar")
public class PanelSolarController {

  @Autowired
  private final PanelSolarService panelSolarService;

  public PanelSolarController(PanelSolarService panelSolarService) {
    this.panelSolarService = panelSolarService;
  }

  @PostMapping
  public ResponseEntity<PanelSolarDto> createPanelSolar(
    @RequestBody PanelSolarDto panelSolarDto
  ) throws Exception{
    log.info(String.valueOf(panelSolarDto));
    return new ResponseEntity<>(
      panelSolarService.create( panelSolarDto ),
      HttpStatus.OK
    );
  }

  @PatchMapping("/{id}")
  public  ResponseEntity<PanelSolarDto> modify(
    @PathVariable UUID id,
    @RequestBody PanelSolarDto update
  ) throws Exception {
    return new ResponseEntity<>(
      panelSolarService.modified(id, update),
      HttpStatus.OK
    );
  }

  @GetMapping("/all")
  public ResponseEntity<List<PanelSolarDto>> getAllAireVentana() throws  Exception {
    return new ResponseEntity<>(
      panelSolarService.getAllPanelSolar(),
      HttpStatus.OK
    );
  }

  @GetMapping("/getbyid/{id}")
  public ResponseEntity<PanelSolarDto> getById (
    @PathVariable UUID id
  ) throws Exception {
    return new ResponseEntity<>(
      panelSolarService.getPanelSolarForId(id),
      HttpStatus.OK
    );
  }
}
