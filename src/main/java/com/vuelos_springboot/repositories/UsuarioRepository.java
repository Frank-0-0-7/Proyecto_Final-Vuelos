package com.vuelos_springboot.repositories;

import com.vuelos_springboot.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Integer> {

}