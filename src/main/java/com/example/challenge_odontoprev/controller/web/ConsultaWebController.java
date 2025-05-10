package com.example.challenge_odontoprev.controller.web;

import com.example.challenge_odontoprev.dto.ConsultaDTO;
import com.example.challenge_odontoprev.dto.TratamentoDTO;
import com.example.challenge_odontoprev.dto.UsuarioDTO;
import com.example.challenge_odontoprev.model.Role;
import com.example.challenge_odontoprev.security.UsuarioDetails;
import com.example.challenge_odontoprev.service.ConsultaService;
import com.example.challenge_odontoprev.service.TratamentoService;
import com.example.challenge_odontoprev.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/web/consultas")
@AllArgsConstructor
public class ConsultaWebController {

    private final ConsultaService consultaService;
    private final UsuarioService usuarioService;
    private final TratamentoService tratamentoService;

    @GetMapping
    public String listarConsultas(Model model,
                                  @AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        List<ConsultaDTO> consultas;

        if (usuarioDetails.getUsuario().getRole() == Role.ADMIN) {
            consultas = consultaService.getAllConsultas();
        } else {
            consultas = consultaService.getConsultasByUsuarioId(usuarioDetails.getUsuario().getId());
        }

        model.addAttribute("consultas", consultas);
        return "consulta-list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model,
                                            @AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        ConsultaDTO consultaDTO = new ConsultaDTO();

        // Admin vê select de usuários, comum é vinculado automaticamente
        if (usuarioDetails.getUsuario().getRole() == Role.ADMIN) {
            model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        } else {
            consultaDTO.setUsuarioId(usuarioDetails.getUsuario().getId());
        }

        model.addAttribute("consulta", consultaDTO);
        model.addAttribute("tratamentos", tratamentoService.getAllTratamentos());
        return "consulta-form";
    }

    @PostMapping("/salvar")
    public String salvarConsulta(@ModelAttribute("consulta") ConsultaDTO consultaDTO,
                                 @AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                 @RequestParam(value = "tratamentosIds", required = false) List<UUID> tratamentosIds,
                                 RedirectAttributes redirectAttributes)
    {
        if (tratamentosIds != null && !tratamentosIds.isEmpty()) {
            List<TratamentoDTO> tratamentos = tratamentoService.findTratamentosByIds(tratamentosIds);
            consultaDTO.setTratamentos(tratamentos);
        }
        try {
            // Garante que usuário comum só crie para si mesmo
            if (usuarioDetails.getUsuario().getRole() != Role.ADMIN) {
                consultaDTO.setUsuarioId(usuarioDetails.getUsuario().getId());
            }

            consultaService.saveConsulta(consultaDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Consulta salva com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar consulta: " + e.getMessage());
        }

        return "redirect:/web/consultas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable UUID id, Model model,
                                          @AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        ConsultaDTO consultaDTO = consultaService.getConsultaById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada"));

        // Verifica permissão
        if (usuarioDetails.getUsuario().getRole() != Role.ADMIN &&
                !consultaDTO.getUsuarioId().equals(usuarioDetails.getUsuario().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }

        // Configura o formulário conforme o tipo de usuário
        if (usuarioDetails.getUsuario().getRole() == Role.ADMIN) {
            model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        } else {
            consultaDTO.setUsuarioId(usuarioDetails.getUsuario().getId());
        }

        model.addAttribute("consulta", consultaDTO);
        model.addAttribute("tratamentos", tratamentoService.getAllTratamentos());
        return "consulta-form";
    }

    @GetMapping("/deletar/{id}")
    public String deletarConsulta(@PathVariable UUID id,
                                  @AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                  RedirectAttributes redirectAttributes) {
        try {
            ConsultaDTO consultaDTO = consultaService.getConsultaById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada"));

            // Verifica permissão
            if (usuarioDetails.getUsuario().getRole() != Role.ADMIN &&
                    !consultaDTO.getUsuarioId().equals(usuarioDetails.getUsuario().getId())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
            }

            consultaService.deleteConsulta(id);
            redirectAttributes.addFlashAttribute("successMessage", "Consulta deletada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao deletar consulta: " + e.getMessage());
        }
        return "redirect:/web/consultas";
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

