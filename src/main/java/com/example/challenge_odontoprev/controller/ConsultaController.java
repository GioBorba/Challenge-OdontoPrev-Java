package com.example.challenge_odontoprev.controller;

import com.example.challenge_odontoprev.dto.ConsultaDTO;
import com.example.challenge_odontoprev.model.Consulta;
import com.example.challenge_odontoprev.service.ConsultaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaDTO> createConsulta(@RequestBody ConsultaDTO consultaDTO) {
        ConsultaDTO createdConsulta = consultaService.saveConsulta(consultaDTO);
        return ResponseEntity.status(201).body(createdConsulta);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> getAllConsultas() {
        List<ConsultaDTO> consultas = consultaService.getAllConsultas();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable UUID id) {
        Optional<ConsultaDTO> consulta = consultaService.getConsultaById(id);
        return consulta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable UUID id) {
        consultaService.deleteConsulta(id);
        return ResponseEntity.noContent().build();
    }

    // lista consultas pelo id de usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ConsultaDTO>> getConsultasByUsuarioId(@PathVariable UUID usuarioId) {
        List<ConsultaDTO> consultas = consultaService.getConsultasByUsuarioId(usuarioId);
        return ResponseEntity.ok(consultas);
    }

}
