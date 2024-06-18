package com.example.apistock.models.entities;

import com.example.apistock.models.entities.grupoelectrogeno.grupo;
import com.example.apistock.models.entities.rectifcadores.SistemaRectificador;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id")
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

  @OneToMany(targetEntity = Split.class, mappedBy = "center")
  private List<Split> splits;

  @OneToMany(
    targetEntity = Inversor.class,
    mappedBy = "center",
    cascade = {
      CascadeType.MERGE,
      CascadeType.PERSIST
    }
  )
  private List<Inversor> inversors;

  @OneToMany(targetEntity = PanelSolar.class, mappedBy = "center")
  private List<PanelSolar> panelSolars;

  @OneToMany(targetEntity = Presurizador.class, mappedBy = "center")
  private List<Presurizador> presurizadors;

  @OneToMany(targetEntity = AireVentana.class, mappedBy = "center")
  private List<AireVentana> aireVentanas;

  @OneToMany(targetEntity = SistemaRectificador.class, mappedBy = "center")
  private List<SistemaRectificador> sistemaRectificadors;

  @OneToMany(targetEntity = grupo.class, mappedBy = "center")
  private List<grupo> grupos;
}
