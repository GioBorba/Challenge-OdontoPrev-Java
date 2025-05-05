package com.example.challenge_odontoprev.controller.web;

import com.example.challenge_odontoprev.dto.LembreteDTO;
import com.example.challenge_odontoprev.service.LembreteService;
import com.example.challenge_odontoprev.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/web/lembretes")
@AllArgsConstructor
public class LembreteWebController {

    private final LembreteService lembreteService;
    private final UsuarioService usuarioService;

    @GetMapping
    public String listarLembretes(Model model) {
        model.addAttribute("lembretes", lembreteService.getAllLembretes());
        return "lembrete-list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("lembrete", new LembreteDTO());
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        return "lembrete-form";
    }

    @PostMapping("/salvar")
    public String salvarLembrete(@ModelAttribute("lembrete") LembreteDTO lembreteDTO,
                                 RedirectAttributes redirectAttributes) {
        try {
            lembreteService.saveLembrete(lembreteDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Lembrete salvo com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar lembrete: " + e.getMessage());
        }
        return "redirect:/web/lembretes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable UUID id, Model model) {
        lembreteService.getLembreteById(id).ifPresentOrElse(
                lembrete -> {
                    model.addAttribute("lembrete", lembrete);
                    model.addAttribute("usuarios", usuarioService.getAllUsuarios());
                },
                () -> model.addAttribute("errorMessage", "Lembrete não encontrado")
        );
        return "lembrete-form";
    }

    @GetMapping("/deletar/{id}")
    public String deletarLembrete(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            lembreteService.deleteLembrete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Lembrete deletado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao deletar lembrete: " + e.getMessage());
        }
        return "redirect:/web/lembretes";
    }

    @GetMapping("/usuario/{usuarioId}")
    public String listarLembretesPorUsuario(@PathVariable UUID usuarioId, Model model) {
        model.addAttribute("lembretes", lembreteService.getLembretesByUsuarioId(usuarioId));
        return "lembrete-list";
    }
}