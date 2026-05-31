package com.vuelos_springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "asientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asiento implements Base<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asiento")
    private int idAsiento;

    @Column(name = "fila_asiento")
    private int filaAsiento;

    @Column(name = "letra_asiento")
    private char letraAsiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "clase_asiento")
    private Clase claseAsiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_avion", nullable = false)
    @JsonIgnore
    private Avion avion;

    @OneToOne(mappedBy = "asiento", fetch = FetchType.LAZY)
    @JsonIgnore
    private Reserva reserva;

    @Override
    public String toString() {
        return filaAsiento + "" + letraAsiento + " (" + claseAsiento + ")";
    }

    @Override
    public Integer getId() {
        return this.idAsiento;
    }
}
