package com.example.apistock.models.entities.rectifcadores;

import com.example.apistock.models.entities.AireVentana;
import com.example.apistock.models.entities.Center;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class SistemaRectificador {
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
    private String country;
    @Column
    private String dateInstalacion;

    @OneToMany(targetEntity = ModuloRectificador.class)
    private List<ModuloRectificador> moduloRectificadors;

    @OneToMany(targetEntity = ModuloControl.class)
    private List<ModuloControl> moduloControls;

    @ManyToOne
    private  Center center;
}
