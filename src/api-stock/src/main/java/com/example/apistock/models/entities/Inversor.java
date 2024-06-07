package com.example.apistock.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
public class Inversor {
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
    private String principalUsage;
    @Column
    private String numActivo;
    @Column
    private String marca;
    @Column
    private String model;
    @Column
    private String country;
    @Column
    private String cantModules;
    @Column
    private Double DC;
    @Column
    private Double AC;
    @Column
    private Double VA;
    @Column
    private String dateInstalation;
    @Column
    private String tecnicalStatus;
    @Column
    private Integer priorityLevel;

    @ManyToOne(targetEntity = Center.class)
    private  Center center;
}
