package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Persona implements Base<Integer> {

    @Id
    @Column(name = "dni_persona", nullable = false)
    private int dniPersona;

    @Column(name = "nombre_persona", length = 100, nullable = false)
    private String nombrePersona;

    @Column(name = "apellido_persona", length = 100, nullable = false)
    private String apellidoPersona;

    @Override
    public String toString() {
        return nombrePersona + " " + apellidoPersona + " (DNI: " + dniPersona + ")";
    }

    @Override
    public Integer getId() {
        return this.dniPersona;
    }
}
