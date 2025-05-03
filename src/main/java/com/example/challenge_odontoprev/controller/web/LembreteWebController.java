package com.example.challenge_odontoprev.controller.web;

import com.example.challenge_odontoprev.dto.LembreteDTO;
import com.example.challenge_odontoprev.service.LembreteService;
import com.example.challenge_odontoprev.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String salvarLembrete(@ModelAttribute("lembrete") LembreteDTO lembreteDTO) {
        lembreteService.saveLembrete(lembreteDTO);
        return "redirect:/web/lembretes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable UUID id, Model model) {
        LembreteDTO lembreteDTO = lembreteService.getLembreteById(id)
                .orElseThrow(() -> new RuntimeException("Lembrete não encontrado"));
        model.addAttribute("lembrete", lembreteDTO);
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        return "lembrete-form";
    }

    @GetMapping("/deletar/{id}")
    public String deletarLembrete(@PathVariable UUID id) {
        lembreteService.deleteLembrete(id);
        return "redirect:/web/lembretes";
    }
}