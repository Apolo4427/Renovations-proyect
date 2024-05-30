package com.renovations.jrl.apirestrenovations.Repositories;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.renovations.jrl.apirestrenovations.Entities.PagosClientes;
import com.renovations.jrl.apirestrenovations.Entities.PagosParaAliados;
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

    @Test
    public void testAddPagosparaAliados(){
        Proyecto proyecto = proyectoRepository.findByNumeroContrato("123");

        PagosParaAliados pagoAAliado = PagosParaAliados.builder().empresaAliada("los del barrio")
                                                                .fechaDePago("16 de febrero")
                                                                .valorPagado("muchisimo")
                                                                .build();
        
        List<PagosParaAliados> listaDePagos = proyecto.getListaDePagosAliados();
        
        listaDePagos.add(pagoAAliado);
        proyecto.setListaDePagosAliados(listaDePagos);
        proyectoRepository.save(proyecto);
    }
}
