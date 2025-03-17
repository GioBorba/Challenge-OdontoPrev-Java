package com.example.challenge_odontoprev.controller.api;

import com.example.challenge_odontoprev.dto.ConsultaDTO;
import com.example.challenge_odontoprev.service.ConsultaService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
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

        // Adicionando links HATEOAS
        createdConsulta.add(Link.of("/consultas/" + createdConsulta.getId()).withSelfRel());
        createdConsulta.add(Link.of("/consultas").withRel("allConsultas"));
        createdConsulta.add(Link.of("/consultas/usuario/" + createdConsulta.getUsuarioId()).withRel("consultasPorUsuario"));

        return ResponseEntity.status(201).body(createdConsulta);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> getAllConsultas() {
        List<ConsultaDTO> consultas = consultaService.getAllConsultas();

        // Adicionando links HATEOAS para cada consulta
        consultas.forEach(consulta -> {
            consulta.add(Link.of("/consultas/" + consulta.getId()).withSelfRel());
            consulta.add(Link.of("/consultas").withRel("allConsultas"));
            consulta.add(Link.of("/consultas/usuario/" + consulta.getUsuarioId()).withRel("consultasPorUsuario"));
        });

        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable UUID id) {
        Optional<ConsultaDTO> consulta = consultaService.getConsultaById(id);

        if (consulta.isPresent()) {
            // Adicionando links HATEOAS
            consulta.get().add(Link.of("/consultas/" + consulta.get().getId()).withSelfRel());
            consulta.get().add(Link.of("/consultas").withRel("allConsultas"));
            consulta.get().add(Link.of("/consultas/usuario/" + consulta.get().getUsuarioId()).withRel("consultasPorUsuario"));
            return ResponseEntity.ok(consulta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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

        // Adicionando links HATEOAS para cada consulta
        consultas.forEach(consulta -> {
            consulta.add(Link.of("/consultas/" + consulta.getId()).withSelfRel());
            consulta.add(Link.of("/consultas").withRel("allConsultas"));
            consulta.add(Link.of("/consultas/usuario/" + consulta.getUsuarioId()).withRel("consultasPorUsuario"));
        });

        return ResponseEntity.ok(consultas);
    }
}
