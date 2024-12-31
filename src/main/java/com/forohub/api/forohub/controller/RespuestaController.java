package com.forohub.api.forohub.controller;

import com.forohub.api.forohub.domain.respuesta.*;
import com.forohub.api.forohub.domain.topico.DatosActualizarTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoRespuesta> obtenerRespuesta(@PathVariable String id){
        DatosListadoRespuesta datosRespuesta = respuestaService.obtenerRespuesta(id);
        return ResponseEntity.ok(datosRespuesta);
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistrarRespuesta datosRegistrarRespuesta) {
        Respuesta respuesta = respuestaService.crearRespuesta(datosRegistrarRespuesta);
        DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(respuesta.getId(),respuesta.getMensaje(),
                respuesta.getTopicoId(),respuesta.getFechaCreacion(),respuesta.getAutorId(),respuesta.getSolucion());
        return ResponseEntity.ok(datosRespuestaRespuesta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta){
        Respuesta respuesta = respuestaService.actualizarRespuesta(datosActualizarRespuesta);
        DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(respuesta.getId(),respuesta.getMensaje(),
                respuesta.getTopicoId(),respuesta.getFechaCreacion(),respuesta.getAutorId(),respuesta.getSolucion());
        return ResponseEntity.ok(datosRespuestaRespuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable UUID id){
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.ok().build();
    }
}
