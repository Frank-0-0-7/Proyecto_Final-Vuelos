package com.vuelos_springboot.repositories;

import com.vuelos_springboot.entities.Reserva;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends BaseRepository<Reserva, Integer> {

}