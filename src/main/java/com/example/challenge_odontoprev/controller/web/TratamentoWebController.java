package com.example.challenge_odontoprev.controller.web;

import com.example.challenge_odontoprev.dto.TratamentoDTO;
import com.example.challenge_odontoprev.service.TratamentoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/web/tratamentos")
@AllArgsConstructor
public class TratamentoWebController {

    private final TratamentoService tratamentoService;

    // Lista todos os tratamentos
    @GetMapping
    public String listarTratamentos(Model model) {
        model.addAttribute("tratamentos", tratamentoService.getAllTratamentos());
        return "tratamento-list"; // Retorna a view tratamento-list.html
    }

    // Exibe o formulário de cadastro
    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("tratamento", new TratamentoDTO());
        return "tratamento-form"; // Retorna a view tratamento-form.html
    }

    // Salva um novo tratamento
    @PostMapping("/salvar")
    public String salvarTratamento(@ModelAttribute("tratamento") TratamentoDTO tratamentoDTO) {
        tratamentoService.saveTratamento(tratamentoDTO);
        return "redirect:/web/tratamentos"; // Redireciona para a lista de tratamentos
    }

    // Exibe o formulário de edição
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable UUID id, Model model) {
        TratamentoDTO tratamentoDTO = tratamentoService.getTratamentoById(id)
                .orElseThrow(() -> new RuntimeException("Tratamento não encontrado"));
        model.addAttribute("tratamento", tratamentoDTO);
        return "tratamento-form"; // Retorna a view tratamento-form.html
    }

    // Deleta um tratamento
    @GetMapping("/deletar/{id}")
    public String deletarTratamento(@PathVariable UUID id) {
        tratamentoService.deleteTratamento(id);
        return "redirect:/web/tratamentos"; // Redireciona para a lista de tratamentos
    }
}