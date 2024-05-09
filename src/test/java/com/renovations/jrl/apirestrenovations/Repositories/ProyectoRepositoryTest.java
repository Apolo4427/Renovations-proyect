package com.renovations.jrl.apirestrenovations.Repositories;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.renovations.jrl.apirestrenovations.Entities.PagosClientes;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;

@SpringBootTest
public class ProyectoRepositoryTest {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Test
    public void testFindByNumero_contrato() {
        Proyecto proyecto = proyectoRepository.findByNumeroContrato("123");
        List<PagosClientes> pagos = proyecto.getListaDePagosClientes();

        PagosClientes ultimoPago = PagosClientes.builder().fecha_pago("31 de enero").valor_pagado("no mucho").build();

        pagos.add(ultimoPago);
        proyecto.setListaDePagosClientes(pagos);
        proyectoRepository.save(proyecto);
        
    }
}
