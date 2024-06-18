package com.example.apistock.controller;

import com.example.apistock.models.dto.CenterDto;
import com.example.apistock.models.entities.Center;
import com.example.apistock.services.center.CenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("v1/api/center")
public class CenterController {

    private final CenterService centerService;

    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @PostMapping
    public ResponseEntity<CenterDto> createCenter(
            @RequestBody CenterDto centerDto
    ) throws Exception{
        log.info(String.valueOf(centerDto));
        return new ResponseEntity<>(
                centerService.create(centerDto),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CenterDto> modify(
            @PathVariable UUID id,
            @RequestBody CenterDto update
    ) throws Exception{
        log.info(String.valueOf(update));
        return new ResponseEntity<>(
                centerService.modified(id,update),
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<Center>> getAllCenter() throws Exception{
        return new ResponseEntity<>(
                centerService.getAllCenter(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<CenterDto> getbyId(
            @PathVariable UUID id
    ) throws Exception{

        return new ResponseEntity<>(
                centerService.getCenterForId(id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
      @PathVariable UUID id
    )throws Exception{
      return  new ResponseEntity<>(
        centerService.delete(id),
        HttpStatus.OK
      );
    }

}
