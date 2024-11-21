package com.clinica.controller;

import com.clinica.model.Paciente;
import com.clinica.repository.PacienteRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // Listar todos os pacientes
    @GetMapping
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    // Criar um novo paciente
    @PostMapping
    public Paciente criar(@Valid @RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
}
