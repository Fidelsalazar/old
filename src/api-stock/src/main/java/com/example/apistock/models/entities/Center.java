package com.example.apistock.models.entities;

import com.example.apistock.models.entities.grupoelectrogeno.grupo;
import com.example.apistock.models.entities.rectifcadores.SistemaRectificador;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Center {
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
    private String center;
    @Column
    private String sitio;

    @OneToMany(targetEntity = Split.class)
    @JsonIgnore
    private List<Split> splits;
    @JsonIgnore
    @OneToMany(targetEntity = Inversor.class)
    private List<Inversor> inversors;
    @JsonIgnore
    @OneToMany(targetEntity = PanelSolar.class)
    private List<PanelSolar> panelSolars;
    @JsonIgnore
    @OneToMany(targetEntity = Presurizador.class)
    private List<Presurizador> presurizadors;
    @JsonIgnore
    @OneToMany(targetEntity = AireVentana.class)
    private List<AireVentana> aireVentanas;
    @JsonIgnore
    @OneToMany(targetEntity = SistemaRectificador.class)
    private List<SistemaRectificador> sistemaRectificadors;
    @JsonIgnore
    @OneToMany(targetEntity = grupo.class)
    private List<grupo> grupos;

}
