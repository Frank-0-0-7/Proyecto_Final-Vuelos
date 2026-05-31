package com.vuelos_springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tarifas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarifa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_tarifa")
    private int numeroTarifa;

    @Column(name = "impuesto_tarifa", precision = 10, scale = 2, nullable = false)
    private BigDecimal impuestoTarifa;

    @Column(name = "precio_tarifa", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioTarifa;

    @Enumerated(EnumType.STRING)
    @Column(name = "clase_tarifa", nullable = false)
    private Clase claseTarifa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_vuelo", nullable = false)
    @JsonIgnore
    private Vuelo vuelo;
}
