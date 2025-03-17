package com.example.challenge_odontoprev.controller.api;

import com.example.challenge_odontoprev.dto.TratamentoDTO;
import com.example.challenge_odontoprev.service.TratamentoService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/tratamentos")
public class TratamentoController {

    private final TratamentoService tratamentoService;

    // Criando um novo tratamento
    @PostMapping
    public ResponseEntity<TratamentoDTO> createTratamento(@RequestBody TratamentoDTO tratamentoDTO) {
        TratamentoDTO createdTratamento = tratamentoService.saveTratamento(tratamentoDTO);

        // Adicionando links HATEOAS
        createdTratamento.add(Link.of("/tratamentos/" + createdTratamento.getId()).withSelfRel());
        createdTratamento.add(Link.of("/tratamentos").withRel("allTratamentos"));

        return ResponseEntity.status(201).body(createdTratamento);
    }

    // Listando todos os tratamentos
    @GetMapping
    public ResponseEntity<List<TratamentoDTO>> getAllTratamentos() {
        List<TratamentoDTO> tratamentos = tratamentoService.getAllTratamentos();

        // Adicionando links HATEOAS para cada tratamento
        tratamentos.forEach(tratamento -> {
            tratamento.add(Link.of("/tratamentos/" + tratamento.getId()).withSelfRel());
            tratamento.add(Link.of("/tratamentos").withRel("allTratamentos"));
        });

        return ResponseEntity.ok(tratamentos);
    }

    // Consultando um tratamento específico
    @GetMapping("/{id}")
    public ResponseEntity<TratamentoDTO> getTratamentoById(@PathVariable UUID id) {
        Optional<TratamentoDTO> tratamento = tratamentoService.getTratamentoById(id);

        if (tratamento.isPresent()) {
            // Adicionando links HATEOAS
            tratamento.get().add(Link.of("/tratamentos/" + tratamento.get().getId()).withSelfRel());
            tratamento.get().add(Link.of("/tratamentos").withRel("allTratamentos"));
            return ResponseEntity.ok(tratamento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Excluindo um tratamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTratamento(@PathVariable UUID id) {
        tratamentoService.deleteTratamento(id);
        return ResponseEntity.noContent().build();
    }
}
