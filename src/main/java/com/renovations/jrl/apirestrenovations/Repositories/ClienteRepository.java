package com.renovations.jrl.apirestrenovations.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import java.util.List;


@Repository
public interface ClienteRepository extends  JpaRepository<Cliente, Long>{

    /*Optional<Cliente>*/ List<Cliente> findByNombre(String nombre);
}
