package com.vuelos_springboot.repositories;

import com.vuelos_springboot.entities.Base;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<E extends Base<ID>, ID extends Serializable> extends JpaRepository<E, ID> {
}
