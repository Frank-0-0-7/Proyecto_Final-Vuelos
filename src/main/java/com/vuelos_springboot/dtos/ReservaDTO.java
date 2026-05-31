package com.vuelos_springboot.dtos;

import java.math.BigDecimal;

public record ReservaDTO(
        int dni,
        String nombre,
        String apellido,
        int numeroReserva,
        int numeroVuelo,
        BigDecimal montoTotal,
        String aeropuertoDestino,
        String ciudadDestino,
        String fila,
        String letra,
        String numeroAvion,
        String turbina,
        String fechaSalida,
        String aerolinea,
        String numeroPago,
        String numeroTarjeta,
        String tipoTarjeta
) {}