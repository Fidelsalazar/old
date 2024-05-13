package com.example.apistock.models.entities.panelesElectricos;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Interruptor {
    @Id
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private UUID id;

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Column
    private String model;
    @Column
    private Integer numPolos;
    @Column
    private Double corrienteNominal;
    @Column
    private String curvaDisparos;
    @Column
    private Integer corrienteCarga;
    @Column
    private String tecnicalStatus;
}
