package com.clinica.controller;

import com.clinica.model.Consulta;
import com.clinica.model.Medico;
import com.clinica.model.Paciente;
import com.clinica.repository.ConsultaRepository;
import com.clinica.repository.MedicoRepository;
import com.clinica.repository.PacienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/consultas")
public class ConsultaController {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    // Injeção de dependências dos repositórios
    public ConsultaController(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    // Listar todas as consultas
    @GetMapping
    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    // Criar uma nova consulta
    @PostMapping
    public Consulta criar(@RequestBody Consulta consulta) {
        // Buscar e associar o paciente pelo ID
        if (consulta.getPaciente() != null && consulta.getPaciente().getId() != null) {
            Paciente paciente = pacienteRepository.findById(consulta.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
            consulta.setPaciente(paciente);
        } else {
            throw new RuntimeException("O ID do paciente é obrigatório.");
        }

        // Buscar e associar os médicos pelos IDs
        if (consulta.getMedicos() != null && !consulta.getMedicos().isEmpty()) {
            List<Medico> medicos = consulta.getMedicos().stream()
                .map(m -> medicoRepository.findById(m.getId())
                    .orElseThrow(() -> new RuntimeException("Médico com ID " + m.getId() + " não encontrado")))
                .collect(Collectors.toList());
            consulta.setMedicos(medicos);
        }

        // Salvar a consulta
        return consultaRepository.save(consulta);
    }

    // Excluir uma consulta
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        consultaRepository.deleteById(id);
    }

    // Alterar uma consulta existente
    @PutMapping("/{id}")
    public Consulta alterar(@PathVariable Long id, @RequestBody Consulta consultaAlterada) {
        // Verificar se a consulta com o id fornecido existe
        Optional<Consulta> consultaExistente = consultaRepository.findById(id);
        
        if (consultaExistente.isPresent()) {
            Consulta consulta = consultaExistente.get();

            // Atualizar os dados da consulta com os valores recebidos
            consulta.setPaciente(consultaAlterada.getPaciente());
            consulta.setMedicos(consultaAlterada.getMedicos());
            consulta.setDataHora(consultaAlterada.getDataHora());
            // Adicione outras propriedades que precisam ser atualizadas

            return consultaRepository.save(consulta);
        } else {
            // Caso a consulta não exista, lançar uma exceção ou retornar uma resposta adequada
            throw new RuntimeException("Consulta não encontrada");
        }
    }
}
