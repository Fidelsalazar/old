package com.example.apistock.services.aireVentana;

import com.example.apistock.repositories.AireVentanaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AireVentanaServiceImpl implements AireVentanaService{

    @Autowired
    private final AireVentanaRepository aireVentanaRepository;

    public AireVentanaServiceImpl(AireVentanaRepository aireVentanaRepository) {
        this.aireVentanaRepository = aireVentanaRepository;
    }

}
