package com.example.apistock.models.entities.grupoelectrogeno;

import com.example.apistock.models.entities.Center;
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
public class grupo {
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
    private String numSerie;
    @Column
    private String annoFabricante;
    @Column
    private Double Ih;
    @Column
    private String tecnicalStatus;
    @Column
    private Integer priorityLevel;
    @Column
    private Boolean supervisado;
    @Column
    private Boolean gestionado;

    @OneToOne
    private generador generador;
    @OneToOne
    private  motor motor;
    @ManyToOne
    private Center center;
}
