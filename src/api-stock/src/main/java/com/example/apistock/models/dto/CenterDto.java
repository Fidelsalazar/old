package com.example.apistock.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CenterDto {
    private  UUID id;
    private String center;
    private String sitio;

    public CenterDto(String center, String sitio) {
        this.center = center;
        this.sitio = sitio;
    }
}
