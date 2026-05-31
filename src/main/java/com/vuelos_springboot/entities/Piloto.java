package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pilotos")
@PrimaryKeyJoinColumn(name = "dni_persona")
@Getter
@Setter
@NoArgsConstructor
public class Piloto extends Persona implements Serializable {

    @Column(name = "numero_piloto", insertable = false, updatable = false)
    private Integer numeroPiloto;

    @OneToMany(mappedBy = "piloto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vuelo> vuelos = new ArrayList<>();

    public Piloto(Integer numeroPiloto, int dniPersona, String nombrePersona, String apellidoPersona) {
        super(dniPersona, nombrePersona, apellidoPersona);
        this.numeroPiloto = numeroPiloto;
    }

    public void addVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
        vuelo.setPiloto(this);
    }
}