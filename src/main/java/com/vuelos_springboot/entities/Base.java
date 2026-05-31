package com.vuelos_springboot.entities;

import java.io.Serializable;

public interface Base<ID extends Serializable> extends Serializable {
    ID getId();
}