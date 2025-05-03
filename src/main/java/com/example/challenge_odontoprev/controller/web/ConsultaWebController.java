package com.example.challenge_odontoprev.controller.web;

import com.example.challenge_odontoprev.dto.ConsultaDTO;
import com.example.challenge_odontoprev.service.ConsultaService;
import com.example.challenge_odontoprev.service.TratamentoService;
import com.example.challenge_odontoprev.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/web/consultas")
@AllArgsConstructor
public class ConsultaWebController {

    private final ConsultaService consultaService;
    private final TratamentoService tratamentoService;
    private final UsuarioService usuarioService;

    @GetMapping
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", consultaService.getAllConsultas());
        return "consulta-list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("consulta", new ConsultaDTO());
        model.addAttribute("tratamentos", tratamentoService.getAllTratamentos());
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        return "consulta-form";
    }

    @PostMapping("/salvar")
    public String salvarConsulta(@ModelAttribute("consulta") ConsultaDTO consultaDTO) {
        consultaService.saveConsulta(consultaDTO);
        return "redirect:/web/consultas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable UUID id, Model model) {
        ConsultaDTO consultaDTO = consultaService.getConsultaById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        model.addAttribute("consulta", consultaDTO);
        model.addAttribute("tratamentos", tratamentoService.getAllTratamentos());
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        return "consulta-form";
    }

    @GetMapping("/deletar/{id}")
    public String deletarConsulta(@PathVariable UUID id) {
        consultaService.deleteConsulta(id);
        return "redirect:/web/consultas";
    }
}