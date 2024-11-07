package com.example.challenge_odontoprev.dto;

import com.example.challenge_odontoprev.model.Usuario;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Data
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {
    private UUID id;
    private String nome;
    private String email;
    private String senha;
}
