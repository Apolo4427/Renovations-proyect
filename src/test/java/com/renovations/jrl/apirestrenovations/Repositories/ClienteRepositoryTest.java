package com.renovations.jrl.apirestrenovations.Repositories;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.Entities.PagosClientes;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;

@SpringBootTest
public class ClienteRepositoryTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    public void saveCliente(){
        Cliente cliente = Cliente.builder().nombre("Sergio")
                                            .direccion("Carrea 40D sur 50")
                                            .contacto("3113742829")
                                            .email("top@gmail.com")
                                            .referido_por("Juanita")
                                            .build();
        
        clienteRepository.save(cliente);                                   
    }

    @Test
    public void findByNameTest(){
       List<Cliente> clientes = clienteRepository.findByNombre("Sergio");
       System.out.println(clientes);
    }

    @Test
    public void saveClienteWhitProyectos(){

        PagosClientes pagos = PagosClientes.builder().fecha_pago("30 de enero").valor_pagado("un monton").build();

        Proyecto proyecto = Proyecto.builder().numeroContrato("123")
                                            .contratante("Don Ramon")
                                            .listaDePagosClientes(List.of(pagos)).build();

        Cliente cliente = Cliente.builder().nombre("Sergio")
                                            .direccion("Carrea 40D sur 50")
                                            .contacto("3113742829")
                                            .email("careloco12@gmail.com")
                                            .referido_por("Juanita")
                                            .proyectosList(List.of(proyecto))
                                            .build();

        clienteRepository.save(cliente);
    }

    @Test
    public void findAllCleintesWithProyects(){
        List<Cliente> clientes = clienteRepository.findAll();
        System.out.println(clientes);
    }

    @Test
    public void saveProyectoByCliente(){
        Cliente cliente = clienteRepository.findByEmail("top152695@gmail.com");
        
        PagosClientes pagos = PagosClientes.builder().fecha_pago("30 de enero").valor_pagado("un monton").build();

        Proyecto proyecto = Proyecto.builder().contratante("Don Ramon")
                                                .listaDePagosClientes(List.of(pagos)).build();

        cliente.setProyectosList(List.of(proyecto));
        clienteRepository.save(cliente);
        System.out.println(cliente);
    }


}
