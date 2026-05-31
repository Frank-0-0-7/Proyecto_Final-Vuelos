package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ciudades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ciudad implements Serializable {

    @Id
    @Column(name = "nombre_ciudad", length = 45, nullable = false)
    private String nombreCiudad;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Aeropuerto> aeropuertos = new ArrayList<>();

    @Override
    public String toString() {
        return nombreCiudad;
    }
}