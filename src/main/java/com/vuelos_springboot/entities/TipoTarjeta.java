package com.vuelos_springboot.entities;

import lombok.Getter;

@Getter
public enum TipoTarjeta {
    DEBITO("Débito"),
    CREDITO("Crédito");

    private final String descripcion;

    TipoTarjeta(String descripcion) {
        this.descripcion = descripcion;
    }
}