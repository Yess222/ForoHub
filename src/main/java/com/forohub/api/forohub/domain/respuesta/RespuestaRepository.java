package com.forohub.api.forohub.domain.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RespuestaRepository extends JpaRepository<Respuesta, UUID> {
}
