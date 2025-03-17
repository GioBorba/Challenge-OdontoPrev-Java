package com.example.challenge_odontoprev.service;

import com.example.challenge_odontoprev.dto.UsuarioDTO;
import com.example.challenge_odontoprev.model.Usuario;
import com.example.challenge_odontoprev.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario;
        if (usuarioDTO.getId() != null) {
            // Atualiza o usuário existente
            usuario = usuarioRepository.findById(usuarioDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            usuario.setNome(usuarioDTO.getNome());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setSenha(usuarioDTO.getSenha());
        } else {
            // Cria um novo usuário
            usuario = new Usuario();
            usuario.setNome(usuarioDTO.getNome());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setSenha(usuarioDTO.getSenha());
        }
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return toDto(savedUsuario);
    }

    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<UsuarioDTO> getUsuarioById(UUID id) {
        return usuarioRepository.findById(id).map(this::toDto);
    }

    public void deleteUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        return dto;
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        return usuario;
    }


}
