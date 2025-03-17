package com.example.challenge_odontoprev.controller.api;

import com.example.challenge_odontoprev.dto.UsuarioDTO;
import com.example.challenge_odontoprev.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Criando um novo usuário
    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO createdUsuario = usuarioService.saveUsuario(usuarioDTO);

        // Adicionando links HATEOAS
        createdUsuario.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getUsuarioById(createdUsuario.getId())).withSelfRel());
        createdUsuario.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getAllUsuarios()).withRel("allUsuarios"));

        return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
    }

    // Listando todos os usuários
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();

        // Adicionando links HATEOAS para cada usuário
        usuarios.forEach(usuarioDTO -> {
            usuarioDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getUsuarioById(usuarioDTO.getId())).withSelfRel());
            usuarioDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getAllUsuarios()).withRel("allUsuarios"));
        });

        return ResponseEntity.ok(usuarios);
    }

    // Consultando um usuário específico
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable UUID id) {
        Optional<UsuarioDTO> usuario = usuarioService.getUsuarioById(id);

        return usuario.map(dto -> {
            // Adicionando links HATEOAS
            dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getUsuarioById(dto.getId())).withSelfRel());
            dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getAllUsuarios()).withRel("allUsuarios"));
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluindo um usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
