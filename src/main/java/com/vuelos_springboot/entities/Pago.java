package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago implements Base<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_pago")
    private int numeroPago;

    @Column(name = "cantidad_pago", precision = 10, scale = 2)
    private BigDecimal cantidadPago;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_reserva", unique = true, nullable = false)
    private Reserva reserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_tarjeta", nullable = false)
    private Tarjeta tarjeta;

    @Override
    public Integer getId() {
        return this.numeroPago;
    }
}
