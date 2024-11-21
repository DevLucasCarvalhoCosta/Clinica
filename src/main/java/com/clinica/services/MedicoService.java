package com.clinica.services;

import com.clinica.model.Medico;
import com.clinica.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    // Método para salvar um médico no banco de dados
    public Medico salvarMedico(Medico medico) {
        return medicoRepository.save(medico); // Salva o médico no banco e retorna o médico salvo
    }

    // Método para listar todos os médicos (opcional)
    public Iterable<Medico> listarMedicos() {
        return medicoRepository.findAll(); // Retorna todos os médicos cadastrados
    }
}
