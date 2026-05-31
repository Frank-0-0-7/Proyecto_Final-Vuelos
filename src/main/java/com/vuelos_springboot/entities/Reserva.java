package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva implements Base<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_reserva")
    private int numeroReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_vuelo", nullable = false)
    private Vuelo vueloReservado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asiento")
    private Asiento asiento;

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pago pago;

    public void setPago(Pago pago) {
        this.pago = pago;
        if (pago != null) {
            pago.setReserva(this);
        }
    }

    @Override
    public Integer getId() {
        return this.numeroReserva;
    }
}
