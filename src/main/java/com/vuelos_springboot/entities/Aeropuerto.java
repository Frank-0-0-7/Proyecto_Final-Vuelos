package com.vuelos_springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "aeropuertos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aeropuerto implements Serializable {

    @Id
    @Column(name = "nombre_aeropuerto", length = 45, nullable = false)
    private String nombreAeropuerto;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_ciudad", nullable = false)
    private Ciudad ciudad;

    @Override
    public String toString() {
        return nombreAeropuerto;
    }
}