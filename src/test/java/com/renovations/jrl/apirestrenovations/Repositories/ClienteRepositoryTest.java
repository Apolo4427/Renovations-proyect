package com.renovations.jrl.apirestrenovations.Repositories;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;

@SpringBootTest
public class ClienteRepositoryTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    public void saveCliente(){
        Cliente cliente = Cliente.builder().nombre("Sergio")
                                            .direccion("Carrea 40D sur 50")
                                            .contacto("3113742829")
                                            .email("top15@gmail.com")
                                            .referido_por("Juanita")
                                            .fecha_estimado("1/05/2024")
                                            .build();
        
        clienteRepository.save(cliente);                                   
    }

    @Test
    public void findByNameTest(){
       List<Cliente> clientes = clienteRepository.findByNombre("Sergio");
       System.out.println(clientes);
    }

}
