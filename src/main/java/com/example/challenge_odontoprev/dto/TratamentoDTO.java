package com.example.challenge_odontoprev.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Data
public class TratamentoDTO extends RepresentationModel<TratamentoDTO> {
    private UUID id;
    private String nome;
}
