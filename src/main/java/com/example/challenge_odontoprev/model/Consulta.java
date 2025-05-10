package com.example.challenge_odontoprev.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_CONSULTA")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="id", nullable = false)
    private UUID id;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name ="data", nullable = false)
    private LocalDateTime data;

    @ManyToMany
    @JoinTable(name = "consulta_tratamento", joinColumns = @JoinColumn(name = "consulta_id"), inverseJoinColumns = @JoinColumn(name = "tratamento_id")
    )
    private List<Tratamento> tratamentos;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


}
