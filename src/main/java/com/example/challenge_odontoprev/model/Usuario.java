package com.example.challenge_odontoprev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;


@Data
@Entity
@ToString(exclude = "senha")
@Table(name = "TB_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    //Valida se o email está no formato correto
    @Email(message = "Email inválido")
    @Column(name = "email",unique = true, nullable = false, length = 100)
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 5, message = "A senha deve ter no mínimo 5 caracteres")
    private String senha;
}
