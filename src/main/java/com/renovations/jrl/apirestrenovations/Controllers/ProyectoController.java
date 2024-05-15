package com.renovations.jrl.apirestrenovations.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Services.ProyectoServices.ProyectoServicesImp;

public class ProyectoController {

    @Autowired 
    ProyectoServicesImp proyectoServicesImp;

    //METODOS GET
    @GetMapping("/clientes/proyectos/{id}")
    public List<Proyecto> findAllProyectosById(@PathVariable Long id){
        List<Proyecto> listProyectos = proyectoServicesImp.getAllProyectosClientesById(id);
        return listProyectos;
    }

}
