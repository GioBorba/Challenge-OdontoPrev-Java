package com.example.challenge_odontoprev.controller.web;

import com.example.challenge_odontoprev.dto.ConsultaDTO;
import com.example.challenge_odontoprev.dto.TratamentoDTO;
import com.example.challenge_odontoprev.dto.UsuarioDTO;
import com.example.challenge_odontoprev.service.ConsultaService;
import com.example.challenge_odontoprev.service.TratamentoService;
import com.example.challenge_odontoprev.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/web/consultas")
@AllArgsConstructor
public class ConsultaWebController {

    private final ConsultaService consultaService;
    private final UsuarioService usuarioService; // Para listar usuários no formulário
    private final TratamentoService tratamentoService; // Para listar tratamentos no formulário

    // Lista todas as consultas
    @GetMapping
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", consultaService.getAllConsultas());
        return "consulta-list"; // Retorna a view consulta-list.html
    }

    // Exibe o formulário de cadastro
    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        ConsultaDTO consultaDTO = new ConsultaDTO();
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();
        List<TratamentoDTO> tratamentos = tratamentoService.getAllTratamentos();

        model.addAttribute("consulta", consultaDTO);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("tratamentos", tratamentos);

        return "consulta-form"; // Retorna a view consulta-form.html
    }

    @PostMapping("/salvar")
    public String salvarConsulta(
            @ModelAttribute("consulta") ConsultaDTO consultaDTO,
            @RequestParam(value = "tratamentosIds", required = false) List<UUID> tratamentosIds) {

        if (tratamentosIds != null && !tratamentosIds.isEmpty()) {
            List<TratamentoDTO> tratamentos = tratamentoService.findTratamentosByIds(tratamentosIds);
            consultaDTO.setTratamentos(tratamentos);
        }

        consultaService.saveConsulta(consultaDTO);
        return "redirect:/web/consultas";
    }

    // Deleta uma consulta
    @GetMapping("/deletar/{id}")
    public String deletarConsulta(@PathVariable UUID id) {
        consultaService.deleteConsulta(id);
        return "redirect:/web/consultas"; // Redireciona para a lista de consultas
    }


    @GetMapping("/detalhes/{id}")
    public String mostrarDetalhes(@PathVariable UUID id, Model model) {
        ConsultaDTO consulta = consultaService.getConsultaById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        UsuarioDTO usuario = usuarioService.getUsuarioById(consulta.getUsuarioId())
                .orElse(new UsuarioDTO());

        model.addAttribute("consulta", consulta);
        model.addAttribute("usuario", usuario);
        return "consulta-detalhes";
    }
}