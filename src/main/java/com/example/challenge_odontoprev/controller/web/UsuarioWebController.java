package com.example.challenge_odontoprev.controller.web;

import com.example.challenge_odontoprev.dto.UsuarioDTO;
import com.example.challenge_odontoprev.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/web/usuarios")
@AllArgsConstructor
public class UsuarioWebController {

    private final UsuarioService usuarioService;

    // Lista todos os usuários
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        return "usuario-list"; // Retorna a view usuario-list.html
    }

    // Exibe o formulário de cadastro
    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "usuario-form"; // Retorna a view usuario-form.html
    }

    // Salva um novo usuário
    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
        usuarioService.saveUsuario(usuarioDTO);
        return "redirect:/web/usuarios";
    }

    // Exibe o formulário de edição
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable UUID id, Model model) {
        UsuarioDTO usuarioDTO = usuarioService.getUsuarioById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        model.addAttribute("usuario", usuarioDTO);
        return "usuario-form"; // Retorna a view usuario-form.html
    }

    // Deleta um usuário
    @GetMapping("/deletar/{id}")
    public String deletarUsuario(@PathVariable UUID id) {
        usuarioService.deleteUsuario(id);
        return "redirect:/web/usuarios"; // Redireciona para a lista de usuários
    }
}