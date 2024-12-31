package com.forohub.api.forohub.domain.topico;

import com.forohub.api.forohub.domain.curso.Curso;

import java.time.LocalDateTime;
import java.util.UUID;

public record DatosListadoTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        UUID cursoId,
        String cursoNombre,
        String cursoCategoria
) {
    public DatosListadoTopico(Topico topico, Curso curso) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getAutor(), topico.getCursoId(), curso.getNombre(), curso.getCategoria());
    }
}
