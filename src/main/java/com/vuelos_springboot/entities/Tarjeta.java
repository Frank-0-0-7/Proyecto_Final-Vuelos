package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "tarjetas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarjeta implements Base<String> {

    @Id
    @Column(name = "numero_tarjeta", length = 16, nullable = false)
    private String numeroTarjeta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_tarjeta", nullable = false)
    private TipoTarjeta tipoTarjeta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_usuario", nullable = false)
    private Usuario usuario;


    @Override
    public String getId() {
        return this.numeroTarjeta;
    }
}