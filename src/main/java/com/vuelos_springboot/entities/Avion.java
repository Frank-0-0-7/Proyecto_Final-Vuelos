package com.vuelos_springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aviones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Avion implements Serializable {

    @Id
    @Column(name = "numero_avion", nullable = false)
    private int numeroAvion;

    @Column(name = "tipo_turbina", length = 50)
    private String tipoTurbina;

    @Column(name = "tipo_avion", length = 50)
    private String tipoAvion;

    @OneToMany(mappedBy = "avion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Asiento> asientos = new ArrayList<>();

    @OneToMany(mappedBy = "avion", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Vuelo> vuelos = new ArrayList<>();

    public void addAsiento(Asiento asiento) {
        this.asientos.add(asiento);
        asiento.setAvion(this);
    }

    @Override
    public String toString() {
        return "Avion #" + numeroAvion + " (" + tipoAvion + ")";
    }
}