package com.example.challenge_odontoprev.repository;

import com.example.challenge_odontoprev.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM Usuario u JOIN u.consultas c JOIN c.tratamentos t WHERE t.nome = :tratamentoNome")
    List<Usuario> findByTratamento(@Param("tratamentoNome") String tratamentoNome);
}
