package com.vuelos_springboot.services;

import com.vuelos_springboot.dtos.ReservaDTO;
import com.vuelos_springboot.entities.Reserva;
import java.util.List;

public interface ReservaService extends BaseService<Reserva, Integer> {

    void realizarReservaCompletaConIds(String dniStr, String nombre, String apellido,
                                       String email, String pass,
                                       String numTarjeta, String tipoTarjetaStr,
                                       String montoStr,
                                       int idVuelo, int idAsiento) throws Exception;

    List<ReservaDTO> listarReservasParaTablaWeb(List<Reserva> listaReservas);

}