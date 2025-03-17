package com.example.challenge_odontoprev.service;

import com.example.challenge_odontoprev.dto.LembreteDTO;
import com.example.challenge_odontoprev.model.Lembrete;
import com.example.challenge_odontoprev.model.Usuario;
import com.example.challenge_odontoprev.repository.LembreteRepository;
import com.example.challenge_odontoprev.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LembreteService {

    private final LembreteRepository lembreteRepository;
    private final UsuarioRepository usuarioRepository;

    // Salva um novo lembrete
    public LembreteDTO saveLembrete(LembreteDTO lembreteDTO) {
        // Verifica se o usuário associado ao lembrete existe
        Usuario usuario = usuarioRepository.findById(lembreteDTO.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Lembrete lembrete = toEntity(lembreteDTO);
        lembrete.setUsuario(usuario);
        Lembrete savedLembrete = lembreteRepository.save(lembrete);

        return toDto(savedLembrete);
    }

    // Retorna todos os lembretes
    public List<LembreteDTO> getAllLembretes() {
        return lembreteRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Retorna um lembrete por ID
    public Optional<LembreteDTO> getLembreteById(UUID id) {
        return lembreteRepository.findById(id).map(this::toDto);
    }

    // Retorna todos os lembretes de um usuário específico
    public List<LembreteDTO> getLembretesByUsuarioId(UUID usuarioId) {
        // Verifica se o usuário existe
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        return lembreteRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Deleta um lembrete por ID
    public void deleteLembrete(UUID id) {
        if (!lembreteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lembrete não encontrado");
        }
        lembreteRepository.deleteById(id);
    }

    private Lembrete toEntity(LembreteDTO dto) {
        Lembrete lembrete = new Lembrete();
        lembrete.setTexto(dto.getTexto());
        lembrete.setHorario(dto.getHorario());
        return lembrete;
    }

    private LembreteDTO toDto(Lembrete lembrete) {
        LembreteDTO dto = new LembreteDTO();
        dto.setId(lembrete.getId());
        dto.setTexto(lembrete.getTexto());
        dto.setHorario(lembrete.getHorario());
        dto.setUsuarioId(lembrete.getUsuario().getId());
        return dto;
    }
}