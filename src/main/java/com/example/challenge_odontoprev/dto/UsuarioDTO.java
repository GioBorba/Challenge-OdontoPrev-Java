package com.example.challenge_odontoprev.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class UsuarioDTO {
    private UUID id;
    private String nome;
    private String email;
}
