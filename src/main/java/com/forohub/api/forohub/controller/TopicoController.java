package com.forohub.api.forohub.controller;

import com.forohub.api.forohub.domain.perfil.DatosRespuestaPerfil;
import com.forohub.api.forohub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 2) Pageable paginacion){
        Page<DatosListadoTopico> page = topicoService.listarTopicos(paginacion);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarPerfil(@RequestBody @Valid DatosRegistrarTopico datosRegistrarTopico) {
        Topico topico = topicoService.crearTopico(datosRegistrarTopico);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),
                topico.getMensaje(),topico.getFechaCreacion(),topico.getAutor(),topico.getCursoId(),topico.getUsuarioId());
        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> obtenerTopico(@PathVariable String id){
        DatosListadoTopico datosRespuestaTopico = topicoService.obtenerTopico(id);
        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoService.actualizarTopico(datosActualizarTopico);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),
                topico.getMensaje(),topico.getFechaCreacion(),topico.getAutor(),topico.getCursoId(),topico.getUsuarioId());
        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable UUID id){
        topicoService.eliminarTopico(id);
        return ResponseEntity.ok().build();
    }
}
