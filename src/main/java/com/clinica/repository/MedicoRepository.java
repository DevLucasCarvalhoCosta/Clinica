package com.clinica.repository;

import com.clinica.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // O JpaRepository já fornece métodos básicos como save, findAll, findById, deleteById, etc.
}
