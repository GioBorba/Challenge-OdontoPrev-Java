package com.example.challenge_odontoprev.service;

import com.example.challenge_odontoprev.dto.UsuarioDTO;
import com.example.challenge_odontoprev.model.Role;
import com.example.challenge_odontoprev.model.Usuario;
import com.example.challenge_odontoprev.repository.UsuarioRepository;
import com.example.challenge_odontoprev.security.UsuarioDetails;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario;
        if (usuarioDTO.getId() != null) {
            // Atualiza o usuário existente
            usuario = usuarioRepository.findById(usuarioDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            usuario.setNome(usuarioDTO.getNome());
            usuario.setEmail(usuarioDTO.getEmail());

            // Só atualiza a senha se foi fornecida e não está vazia
            if (usuarioDTO.getSenha() != null && !usuarioDTO.getSenha().isEmpty()) {
                usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
            }

            usuario.setRole(usuarioDTO.getRole());
        } else {
            // Cria um novo usuário
            usuario = new Usuario();
            usuario.setNome(usuarioDTO.getNome());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha())); // Codifica a senha aqui
            usuario.setRole(usuarioDTO.getRole() != null ? usuarioDTO.getRole() : Role.USER); // Default para USER
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
        dto.setRole(usuario.getRole());
        return dto;
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setRole(dto.getRole());

        return usuario;
    }

    public Map<String, String> getUsuariosMap() {
        return usuarioRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        usuario -> usuario.getId().toString(),
                        Usuario::getNome
                ));
    }


    public Usuario getUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UsuarioDetails) {
            UsuarioDetails usuarioDetails = (UsuarioDetails) auth.getPrincipal();
            return usuarioDetails.getUsuario();
        }
        return null;
    }

    public boolean isAdmin() {
        Usuario user = getUsuarioLogado();
        return user != null && user.isAdmin();
    }



}
