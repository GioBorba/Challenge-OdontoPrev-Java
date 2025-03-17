package com.example.challenge_odontoprev.controller;

import com.example.challenge_odontoprev.dto.LembreteDTO;
import com.example.challenge_odontoprev.service.LembreteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lembretes")
@AllArgsConstructor
public class LembreteController {

    private final LembreteService lembreteService;

    // Cria um novo lembrete
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LembreteDTO createLembrete(@RequestBody LembreteDTO lembreteDTO) {
        return lembreteService.saveLembrete(lembreteDTO);
    }

    // Retorna todos os lembretes
    @GetMapping
    public List<LembreteDTO> getAllLembretes() {
        return lembreteService.getAllLembretes();
    }

    // Retorna um lembrete por ID
    @GetMapping("/{id}")
    public LembreteDTO getLembreteById(@PathVariable UUID id) {
        return lembreteService.getLembreteById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lembrete não encontrado"));
    }

    // Retorna todos os lembretes de um usuário específico
    @GetMapping("/usuario/{usuarioId}")
    public List<LembreteDTO> getLembretesByUsuarioId(@PathVariable UUID usuarioId) {
        return lembreteService.getLembretesByUsuarioId(usuarioId);
    }

    // Deleta um lembrete por ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLembrete(@PathVariable UUID id) {
        lembreteService.deleteLembrete(id);
    }
}