package com.example.apistock.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Presurizador {
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
    private String fabricante;
    @Column
    private String model;
    @Column
    private String country;
    @Column
    private Integer capacidad;
    @Column
    private String dateInstalacion;
    @Column
    private String tecnicalStatus;
    @Column
    private Integer priorityLevel;

    @ManyToOne(targetEntity = Center.class)
    private  Center center;

}
