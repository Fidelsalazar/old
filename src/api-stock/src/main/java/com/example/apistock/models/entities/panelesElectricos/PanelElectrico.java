package com.example.apistock.models.entities.panelesElectricos;

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
public class PanelElectrico {
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
    private String type;
    @Column
    private Integer panelNum;
    @Column
    private String SPD;
    @Column
    private Double repartidor;


}
