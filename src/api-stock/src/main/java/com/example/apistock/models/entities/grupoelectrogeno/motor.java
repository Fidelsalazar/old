package com.example.apistock.models.entities.grupoelectrogeno;

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
public class motor {
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
    private String marca;
    @Column
    private String model;
    @Column
    private String numSerie;
    @Column
    private String country;
    @Column
    private String aceite;
    @Column
    private String capacidadLitros;
    @Column
    private String refrigerante;
    @Column
    private String correa;
    @Column
    private String alternador;
    @Column
    private String motorArranque;
    @Column
    private String capacidadBaterie;

    @OneToOne
    private grupo grupo;
}
