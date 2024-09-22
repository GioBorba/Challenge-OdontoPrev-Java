package com.example.challenge_odontoprev.service;

import com.example.challenge_odontoprev.dto.ConsultaDTO;
import com.example.challenge_odontoprev.dto.TratamentoDTO;
import com.example.challenge_odontoprev.model.Consulta;
import com.example.challenge_odontoprev.model.Tratamento;
import com.example.challenge_odontoprev.repository.ConsultaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;

    public ConsultaDTO saveConsulta(ConsultaDTO consultaDTO) {
        Consulta consulta = toEntity(consultaDTO);
        Consulta savedConsulta = consultaRepository.save(consulta);
        return toDto(savedConsulta);
    }

    public List<ConsultaDTO> getAllConsultas() {
        return consultaRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ConsultaDTO> getConsultaById(UUID id) {
        return consultaRepository.findById(id).map(this::toDto);
    }

    public void deleteConsulta(UUID id) {
        consultaRepository.deleteById(id);
    }

    public List<ConsultaDTO> getConsultasByUsuarioId(UUID usuarioId) {
        return consultaRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ConsultaDTO toDto(Consulta consulta) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setId(consulta.getId());
        dto.setNome(consulta.getNome());
        dto.setData(consulta.getData());

        List<TratamentoDTO> tratamentoDTOs = consulta.getTratamentos().stream()
                .map(this::toTratamentoDto)
                .collect(Collectors.toList());
        dto.setTratamentos(tratamentoDTOs);

        dto.setUsuarioId(consulta.getUsuario().getId());

        return dto;
    }

    private TratamentoDTO toTratamentoDto(Tratamento tratamento) {
        TratamentoDTO dto = new TratamentoDTO();
        dto.setId(tratamento.getId());
        dto.setNome(tratamento.getNome());
        return dto;
    }

    private Consulta toEntity(ConsultaDTO dto) {
        Consulta consulta = new Consulta();
        consulta.setNome(dto.getNome());
        consulta.setData(dto.getData());
        return consulta;
    }
}
