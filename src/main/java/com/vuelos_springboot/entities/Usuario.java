package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@PrimaryKeyJoinColumn(name = "dni_persona")
@Getter
@Setter
@NoArgsConstructor
public class Usuario extends Persona implements Serializable {

    @Column(name = "numero_usuario", insertable = false, updatable = false)
    private Integer numeroUsuario;

    @Column(name = "contrasenia_usuario", nullable = false)
    private String contraseñaUsuario;

    @Column(name = "correo_electronico_usuario", nullable = false, unique = true)
    private String correoUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tarjeta> tarjetas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consulta> consultas = new ArrayList<>();

    public Usuario(int dniPersona, String nombrePersona, String apellidoPersona,
                   String contraseñaUsuario, String correoUsuario) {
        super(dniPersona, nombrePersona, apellidoPersona);
        this.contraseñaUsuario = contraseñaUsuario;
        this.correoUsuario = correoUsuario;
    }

    public void addReserva(Reserva reserva) {
        this.reservas.add(reserva);
        reserva.setUsuario(this);
    }

    public void addTarjeta(Tarjeta tarjeta) {
        this.tarjetas.add(tarjeta);
        tarjeta.setUsuario(this);
    }

    public void addConsulta(Consulta consulta) {
        this.consultas.add(consulta);
        consulta.setUsuario(this);
    }
}
