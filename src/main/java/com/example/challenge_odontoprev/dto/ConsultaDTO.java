package com.example.challenge_odontoprev.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ConsultaDTO extends RepresentationModel<ConsultaDTO> {
    private UUID id;
    private String nome;
    private LocalDateTime data;
    private List<TratamentoDTO> tratamentos;
    private UUID usuarioId;
}
