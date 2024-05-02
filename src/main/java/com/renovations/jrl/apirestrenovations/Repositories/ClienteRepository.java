package com.renovations.jrl.apirestrenovations.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;

@Repository
public interface ClienteRepository extends  JpaRepository<Cliente,UUID>{

    
}
