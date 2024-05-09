package com.renovations.jrl.apirestrenovations.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renovations.jrl.apirestrenovations.Entities.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    Proyecto findByNumeroContrato(String numero_contrato);
}
