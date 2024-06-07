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
public class PanelSolar {
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
    private String marca;
    @Column
    private String model;
    @Column
    private Integer cantPanel;
    @Column
    private Double capacity;
    @Column
    private Double voltaje;
    @Column
    private String date;
    @Column
    private String tecnicalStatus;

    @ManyToOne(targetEntity = Center.class)
    private  Center center;

}
