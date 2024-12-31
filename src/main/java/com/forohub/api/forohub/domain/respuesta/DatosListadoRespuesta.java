package com.forohub.api.forohub.domain.respuesta;

import com.forohub.api.forohub.domain.topico.Topico;
import com.forohub.api.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record DatosListadoRespuesta(
        String mensaje,
        UUID topicoId,
        LocalDateTime fechaCreacion,
        UUID autorId,
        Boolean solucion,
        String topicoTitulo,
        String topicoMensaje,
        String autorNombre

) {
    public DatosListadoRespuesta(Respuesta respuesta, Topico topico, Usuario autor) {
        this(respuesta.getMensaje(), respuesta.getTopicoId(), respuesta.getFechaCreacion(), respuesta.getAutorId(), respuesta.getSolucion(), topico.getTitulo(), topico.getMensaje(), autor.getNombre());
    }
}
