package com.example.challenge_odontoprev.repository;

import com.example.challenge_odontoprev.model.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LembreteRepository extends JpaRepository<Lembrete, UUID> {

    // Método para buscar lembretes por ID do usuário
    List<Lembrete> findByUsuarioId(UUID usuarioId);
}