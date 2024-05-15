package com.renovations.jrl.apirestrenovations.Controllers;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Services.ProyectoServices.ProyectoServicesImp;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProyectoController.class)
public class ProyectoControllerTest extends MockMvcRequestBuilders{

    @Autowired
    private MockMvc mockMvc;//permite simular peticione HTTP (POST, GET, etc)

    @MockBean
    private ProyectoServicesImp proyectoServicesImp;

    private Cliente cliente;

    @BeforeEach
    void setUp(){
        Proyecto proyecto1= Proyecto.builder().build();
        Proyecto proyecto2 = Proyecto.builder().build();
        cliente = Cliente.builder().id(Long.valueOf(1))
                                    .nombre("alejandro")
                                    .contacto("123456")
                                    .proyectosList(List.of(proyecto1, proyecto2))
                                    .build();
    }

    @Test
    void testFindAllProyectosById() throws Exception {
        Mockito.when(proyectoServicesImp.getAllProyectosClientesById(this.cliente.getId())).thenReturn(this.cliente.getProyectosList());

        mockMvc.perform(get("/clientes/proyectos/1").contentType(MediaType.APPLICATION_JSON)).andExpect((ResultMatcher) status().isOk());
    }
}
