package com.example.challenge_odontoprev.repository;

import com.example.challenge_odontoprev.model.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TratamentoRepository extends JpaRepository<Tratamento, UUID> {
}
