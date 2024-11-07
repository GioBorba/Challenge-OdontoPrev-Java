package com.example.challenge_odontoprev.controller;

import com.example.challenge_odontoprev.dto.TratamentoDTO;
import com.example.challenge_odontoprev.service.TratamentoService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public ResponseEntity<EntityModel<TratamentoDTO>> createTratamento(@RequestBody TratamentoDTO tratamentoDTO) {
        TratamentoDTO createdTratamento = tratamentoService.saveTratamento(tratamentoDTO);
        // Adicionando o link HATEOAS
        EntityModel<TratamentoDTO> tratamentoModel = EntityModel.of(createdTratamento);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TratamentoController.class).getTratamentoById(createdTratamento.getId())).withSelfRel();
        Link allLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TratamentoController.class).getAllTratamentos()).withRel("allTratamentos");
        tratamentoModel.add(selfLink, allLink);
        return ResponseEntity.status(201).body(tratamentoModel);
    }

    @GetMapping
    public ResponseEntity<List<TratamentoDTO>> getAllTratamentos() {
        List<TratamentoDTO> tratamentos = tratamentoService.getAllTratamentos();

        return ResponseEntity.ok(tratamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TratamentoDTO>> getTratamentoById(@PathVariable UUID id) {
        Optional<TratamentoDTO> tratamento = tratamentoService.getTratamentoById(id);
        if (tratamento.isPresent()) {
            // Adicionando os links HATEOAS
            EntityModel<TratamentoDTO> tratamentoModel = EntityModel.of(tratamento.get());
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TratamentoController.class).getTratamentoById(id)).withSelfRel();
            Link allLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TratamentoController.class).getAllTratamentos()).withRel("allTratamentos");
            tratamentoModel.add(selfLink, allLink);
            return ResponseEntity.ok(tratamentoModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTratamento(@PathVariable UUID id) {
        tratamentoService.deleteTratamento(id);
        return ResponseEntity.noContent().build();
    }
}
