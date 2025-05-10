package com.example.challenge_odontoprev.controller.web;

import com.example.challenge_odontoprev.dto.UsuarioDTO;
import com.example.challenge_odontoprev.model.Role;
import com.example.challenge_odontoprev.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model, HttpServletRequest request) {
        model.addAttribute("usuario", new UsuarioDTO());

        // Verifica se a requisição veio da página de login
        boolean fromLogin = request.getHeader("Referer") != null &&
                request.getHeader("Referer").contains("/login");
        model.addAttribute("fromLogin", fromLogin);

        return "usuario-form";
    }

    // Salva um novo usuário
    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO,
                                RedirectAttributes redirectAttributes,
                                @RequestParam(required = false) String fromLogin) {
        try {
            if (usuarioDTO.getId() == null && usuarioDTO.getRole() == null) {
                usuarioDTO.setRole(Role.USER);
            }

            usuarioService.saveUsuario(usuarioDTO);

            if (fromLogin != null && fromLogin.equals("true")) {
                redirectAttributes.addFlashAttribute("successMessage", "Conta criada com sucesso! Faça login para continuar.");
            } else {
                redirectAttributes.addFlashAttribute("successMessage", "Usuário salvo com sucesso!");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar usuário: " + e.getMessage());
        }

        return fromLogin != null && fromLogin.equals("true") ? "redirect:/login" : "redirect:/web/usuarios";
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