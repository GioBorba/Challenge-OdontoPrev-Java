package com.example.challenge_odontoprev.repository;

import com.example.challenge_odontoprev.model.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TratamentoRepository extends JpaRepository<Tratamento, UUID> {
}
