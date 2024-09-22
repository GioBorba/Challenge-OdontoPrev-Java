package com.example.challenge_odontoprev.repository;

import com.example.challenge_odontoprev.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {
    List<Consulta> findByUsuarioId(UUID usuarioId);
}
