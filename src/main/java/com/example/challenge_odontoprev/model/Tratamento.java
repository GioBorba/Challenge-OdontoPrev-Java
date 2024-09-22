package com.example.challenge_odontoprev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "TB_TRATAMENTO")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nome_tratamento", nullable = false)
    @NotBlank(message = "O nome do tratamento é obrigatório")
    private String nome;

    @ManyToMany(mappedBy = "tratamentos")
    private List<Consulta> consultas;

}
