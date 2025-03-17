package com.example.challenge_odontoprev.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
public class LembreteDTO {
    private UUID id;
    private String texto;
    private LocalTime horario;
    private UUID usuarioId; // ID do usuário associado ao lembrete


}