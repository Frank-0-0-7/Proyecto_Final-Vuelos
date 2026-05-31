package com.vuelos_springboot.controllers;

import com.vuelos_springboot.dtos.ReservaDTO;
import com.vuelos_springboot.entities.Reserva;
import com.vuelos_springboot.services.ReservaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/reservas")
public class ReservaController extends BaseControllerImpl<Reserva, ReservaServiceImpl, Integer> {

    @PostMapping("/realizar-completa")
    public ResponseEntity<?> realizarReservaCompleta(@RequestBody Map<String, Object> payload) {
        try {
            String dniStr = String.valueOf(payload.get("dniStr"));
            String nombre = String.valueOf(payload.get("nombre"));
            String apellido = String.valueOf(payload.get("apellido"));
            String email = String.valueOf(payload.get("email"));
            String pass = String.valueOf(payload.get("pass"));
            String numTarjeta = String.valueOf(payload.get("numTarjeta"));
            String tipoTarjetaStr = String.valueOf(payload.get("tipoTarjetaStr"));
            String montoStr = String.valueOf(payload.get("montoStr"));

            int idVuelo = (Integer) payload.get("idVuelo");
            int idAsiento = (Integer) payload.get("idAsiento");

            servicio.realizarReservaCompletaConIds(dniStr, nombre, apellido, email, pass,
                    numTarjeta, tipoTarjetaStr, montoStr,
                    idVuelo, idAsiento);

            return ResponseEntity.status(HttpStatus.OK).body("{\"mensaje\":\"¡Reserva realizada con exito!\"}");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/tabla-web")
    public ResponseEntity<?> obtenerReservasParaTabla() {
        try {
            List<Reserva> reservasBD = servicio.findAll();

            List<ReservaDTO> dtosPlanos = servicio.listarReservasParaTablaWeb(reservasBD);

            return ResponseEntity.status(HttpStatus.OK).body(dtosPlanos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}