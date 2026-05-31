package com.vuelos_springboot.entities;

import lombok.Getter;

@Getter
public enum Clase {
    BUSINESS("Ejecutivo"),
    TURISTA("Turista"),
    ECONOMY("Economico");

    private final String descripcion;

    Clase(String descripcion) {
        this.descripcion = descripcion;
    }
}
