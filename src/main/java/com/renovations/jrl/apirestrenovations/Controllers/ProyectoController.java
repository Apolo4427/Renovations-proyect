package com.renovations.jrl.apirestrenovations.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Services.ProyectoServices.ProyectoServicesImp;

@RestController
public class ProyectoController {

    @Autowired 
    ProyectoServicesImp proyectoServicesImp;

    //METODOS GET
    @GetMapping("/clientes/proyectos/{id}")
    public List<Proyecto> findAllProyectosById(@PathVariable Long id){
        List<Proyecto> listProyectos = proyectoServicesImp.getAllProyectosClientesById(id);
        return listProyectos;
    }

    @GetMapping("/clientes/proyectos/email")
    public List<Proyecto> findAllProyectosByEmail(@RequestParam String email){
        List<Proyecto> listPoyectos = proyectoServicesImp.getAllProyectosClienteByEmail(email);
        return listPoyectos;
    }

    @GetMapping("/clientes/proyectos/contrato")
    public Proyecto findProyectoByNumeroContrato(@RequestParam String numeroContrato){
        Proyecto proyecto = proyectoServicesImp.getProyectoByNumeroContrato(numeroContrato);
        return proyecto;
    }

    //METODO POST
    


}
