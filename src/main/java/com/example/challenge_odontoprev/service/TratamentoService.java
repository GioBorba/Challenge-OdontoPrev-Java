package com.example.challenge_odontoprev.service;

import com.example.challenge_odontoprev.dto.TratamentoDTO;
import com.example.challenge_odontoprev.model.Tratamento;
import com.example.challenge_odontoprev.repository.TratamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TratamentoService {
    private final TratamentoRepository tratamentoRepository;

    public TratamentoDTO saveTratamento(TratamentoDTO tratamentoDTO) {
        Tratamento tratamento = toEntity(tratamentoDTO);
        Tratamento savedTratamento = tratamentoRepository.save(tratamento);
        return toDto(savedTratamento);
    }

    public List<TratamentoDTO> getAllTratamentos() {
        return tratamentoRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<TratamentoDTO> getTratamentoById(UUID id) {
        return tratamentoRepository.findById(id).map(this::toDto);
    }

    public void deleteTratamento(UUID id) {
        tratamentoRepository.deleteById(id);
    }

    private TratamentoDTO toDto(Tratamento tratamento) {
        TratamentoDTO dto = new TratamentoDTO();
        dto.setId(tratamento.getId());
        dto.setNome(tratamento.getNome());
        return dto;
    }

    private Tratamento toEntity(TratamentoDTO dto) {
        Tratamento tratamento = new Tratamento();
        tratamento.setNome(dto.getNome());
        return tratamento;
    }
}
