package com.example.challenge_odontoprev.controller;

import com.example.challenge_odontoprev.dto.TratamentoDTO;
import com.example.challenge_odontoprev.service.TratamentoService;
import lombok.AllArgsConstructor;
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

    @PostMapping
    public ResponseEntity<TratamentoDTO> createTratamento(@RequestBody TratamentoDTO tratamentoDTO) {
        TratamentoDTO createdTratamento = tratamentoService.saveTratamento(tratamentoDTO);
        return ResponseEntity.status(201).body(createdTratamento);


    }

    @GetMapping
    public ResponseEntity<List<TratamentoDTO>> getAllTratamentos() {
        List<TratamentoDTO> tratamentos = tratamentoService.getAllTratamentos();
        return ResponseEntity.ok(tratamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratamentoDTO> getTratamentoById(@PathVariable UUID id) {
        Optional<TratamentoDTO> tratamento = tratamentoService.getTratamentoById(id);
        return tratamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTratamento(@PathVariable UUID id) {
        tratamentoService.deleteTratamento(id);
        return ResponseEntity.noContent().build();
    }
}
