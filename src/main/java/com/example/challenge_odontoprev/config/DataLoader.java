package com.example.challenge_odontoprev.config;

import com.example.challenge_odontoprev.model.Role;
import com.example.challenge_odontoprev.model.Usuario;
import com.example.challenge_odontoprev.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadInitialData(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {
            if (!usuarioRepository.existsByEmail("admin@odontoprev.com")) {
                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setEmail("admin@odontoprev.com");
                admin.setSenha(encoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                usuarioRepository.save(admin);
            }

            if (!usuarioRepository.existsByEmail("user@odontoprev.com")) {
                Usuario user = new Usuario();
                user.setNome("Usuário Comum");
                user.setEmail("user@odontoprev.com");
                user.setSenha(encoder.encode("user123"));
                user.setRole(Role.USER);
                usuarioRepository.save(user);
            }
        };
    }
}
