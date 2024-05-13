package com.example.apistock.models.entities;

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
public class Split {
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
    private String numActivo;
    @Column
    private String local;
    @Column
    private String category;
    @Column
    private String marca;
    @Column
    private String modelInterior;
    @Column
    private String modelExterior;
    @Column
    private String country;
    @Column
    private Integer V;
    @Column
    private Integer fases;
    @Column
    private String refrigerante;
    @Column
    private String capacidad;
    @Column
    private String dateInstalation;
    @Column
    private String tecnicalStatus;
    @Column
    private String levelPriority;
    @ManyToOne(targetEntity = Center.class)
    private  Center center;

}
