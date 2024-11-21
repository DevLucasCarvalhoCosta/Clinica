package com.clinica.controller;

import com.clinica.model.Medico;
import com.clinica.services.MedicoService;
import com.clinica.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    // Endpoint para cadastrar um médico
    @PostMapping
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody Medico medico) {
        try {
            Medico novoMedico = medicoService.salvarMedico(medico);
            return ResponseEntity.ok(novoMedico); // Retorna o médico cadastrado com status 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Em caso de erro, retorna status 500
        }
    }

    // Endpoint para listar todos os médicos
    @GetMapping
    public ResponseEntity<Iterable<Medico>> listarMedicos() {
        try {
            Iterable<Medico> medicos = medicoService.listarMedicos();
            return ResponseEntity.ok(medicos); // Retorna lista de médicos
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Em caso de erro
        }
    }
}
