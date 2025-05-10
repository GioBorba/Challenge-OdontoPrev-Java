package com.example.challenge_odontoprev.controller.web;

import com.example.challenge_odontoprev.dto.LembreteDTO;
import com.example.challenge_odontoprev.model.Role;
import com.example.challenge_odontoprev.model.Usuario;
import com.example.challenge_odontoprev.security.UsuarioDetails;
import com.example.challenge_odontoprev.service.LembreteService;
import com.example.challenge_odontoprev.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/web/lembretes")
@AllArgsConstructor
public class LembreteWebController {

    private final LembreteService lembreteService;
    private final UsuarioService usuarioService;

    @GetMapping
    public String listarLembretes(Model model,
                                  @AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        List<LembreteDTO> lembretes;

        if (usuarioDetails.getUsuario().getRole() == Role.ADMIN) {
            lembretes = lembreteService.getAllLembretes();
        } else {
            lembretes = lembreteService.getLembretesByUsuarioId(usuarioDetails.getUsuario().getId());
        }

        model.addAttribute("lembretes", lembretes);
        return "lembrete-list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model,
                                            @AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        LembreteDTO lembreteDTO = new LembreteDTO();

        if (usuarioDetails.getUsuario().getRole() == Role.ADMIN) {
            model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        } else {
            lembreteDTO.setUsuarioId(usuarioDetails.getUsuario().getId());
        }

        model.addAttribute("lembrete", lembreteDTO);
        return "lembrete-form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable UUID id,
                                          Model model,
                                          @AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        LembreteDTO lembreteDTO = lembreteService.getLembreteById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lembrete não encontrado"));

        // Verifica permissão
        if (usuarioDetails.getUsuario().getRole() != Role.ADMIN &&
                !lembreteDTO.getUsuarioId().equals(usuarioDetails.getUsuario().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }

        // Configura o formulário conforme o tipo de usuário
        if (usuarioDetails.getUsuario().getRole() == Role.ADMIN) {
            model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        } else {
            lembreteDTO.setUsuarioId(usuarioDetails.getUsuario().getId());
        }

        model.addAttribute("lembrete", lembreteDTO);
        return "lembrete-form";
    }

    @PostMapping("/salvar")
    public String salvarLembrete(@ModelAttribute("lembrete") LembreteDTO lembreteDTO,
                                 @AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                 RedirectAttributes redirectAttributes) {
        try {
            // Garante que usuário comum só crie para si mesmo
            if (usuarioDetails.getUsuario().getRole() != Role.ADMIN) {
                lembreteDTO.setUsuarioId(usuarioDetails.getUsuario().getId());
            }

            lembreteService.saveLembrete(lembreteDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Lembrete salvo com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar lembrete: " + e.getMessage());
        }
        return "redirect:/web/lembretes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarLembrete(@PathVariable UUID id,
                                  @AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                  RedirectAttributes redirectAttributes) {
        try {
            LembreteDTO lembreteDTO = lembreteService.getLembreteById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lembrete não encontrado"));

            // Verifica permissão
            if (usuarioDetails.getUsuario().getRole() != Role.ADMIN &&
                    !lembreteDTO.getUsuarioId().equals(usuarioDetails.getUsuario().getId())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
            }

            lembreteService.deleteLembrete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Lembrete deletado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao deletar lembrete: " + e.getMessage());
        }
        return "redirect:/web/lembretes";
    }
}
