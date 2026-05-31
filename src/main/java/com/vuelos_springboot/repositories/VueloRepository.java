package com.vuelos_springboot.repositories;

import com.vuelos_springboot.entities.Vuelo;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloRepository extends BaseRepository<Vuelo, Integer> {

}