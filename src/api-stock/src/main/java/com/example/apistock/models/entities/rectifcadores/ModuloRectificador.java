package com.example.apistock.models.entities.rectifcadores;

import com.example.apistock.models.entities.Center;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ModuloRectificador {
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
    private Integer AC;
    @Column
    private Integer DC;
    @Column
    private Integer A;
    @Column
    private Integer KVA;
    @Column
    private Double cantModulo;

    @ManyToOne()
    private  SistemaRectificador sistemaRectificador ;
}
