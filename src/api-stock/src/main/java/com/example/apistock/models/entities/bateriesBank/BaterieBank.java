package com.example.apistock.models.entities.bateriesBank;

import com.example.apistock.models.entities.rectifcadores.ModuloControl;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class BaterieBank {
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
    private Integer V;
    @Column
    private Integer Ah;
    @Column
    private Integer A;
    @Column
    private Integer h;
    @Column
    private String tecnicalStatus;
    @Column
    private Integer priorityLevel;
    @Column
    private String cantBaterie;
    @Column
    private String numActivo;
    @Column
    private String baterieType;
    @Column
    private String regimenDescarga;
    @Column
    private String cantBanks;

    @OneToMany(targetEntity = Baterie.class)
    private List<Baterie> bateries;
}
