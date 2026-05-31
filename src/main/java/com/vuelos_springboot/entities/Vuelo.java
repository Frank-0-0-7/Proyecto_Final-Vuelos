package com.vuelos_springboot.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vuelos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vuelo implements Base<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_vuelo")
    private int numeroVuelo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"vuelos", "asientos"})
    @JoinColumn(name = "numero_avion")
    private Avion avion;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "aeropuerto_origen")
    @JsonIgnoreProperties("ciudad")
    private Aeropuerto aeropuertoOrigen;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "aeropuerto_destino")
    @JsonIgnoreProperties("ciudad")
    private Aeropuerto aeropuertoDestino;

    @OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Tarifa> tarifas = new ArrayList<>();

    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;

    @Column(name = "fecha_llegada")
    private LocalDateTime fechaLlegada;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "numero_piloto")
    @JsonIgnoreProperties("vuelos")
    private Piloto piloto;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_aerolinea")
    @JsonIgnoreProperties("vuelos")
    private Aerolinea aerolinea;

    public void addTarifa(Tarifa tarifa) {
        this.tarifas.add(tarifa);
        tarifa.setVuelo(this);
    }

    @Override
    public String toString() {
        String origenStr = (aeropuertoOrigen != null) ? aeropuertoOrigen.getNombreAeropuerto() : "???";
        String destinoStr = (aeropuertoDestino != null) ? aeropuertoDestino.getNombreAeropuerto() : "???";

        return "Vuelo #" + this.numeroVuelo + " [" + origenStr + " -> " + destinoStr + "]";
    }

    @Override
    public Integer getId() {
        return this.numeroVuelo;
    }
}