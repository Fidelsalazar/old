package com.example.apistock.models.entities.bateriesBank;

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
public class Baterie {
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
    private String fabricante;
    @Column
    private String model;
    @Column
    private Integer V;
    @Column
    private Integer Ah;
    @Column
    private Integer annoFabricacion;

    @ManyToOne()
    private BaterieBank baterieBank;
}
