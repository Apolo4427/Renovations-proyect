package com.renovations.jrl.apirestrenovations.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Services.ProyectoServices.ProyectoServicesImp;

@RestController
public class ProyectoController {

    @Autowired 
    ProyectoServicesImp proyectoServicesImp;

    //METODOS GET
    @GetMapping("/clientes/{id}/proyectos")
    public List<Proyecto> findAllProyectosById(@PathVariable Long id){
        List<Proyecto> listProyectos = proyectoServicesImp.getAllProyectosClientesById(id);
        return listProyectos;
    }

    @GetMapping("/clientes/email/proyectos")
    public List<Proyecto> findAllProyectosByEmail(@RequestParam String email){
        List<Proyecto> listPoyectos = proyectoServicesImp.getAllProyectosClienteByEmail(email);
        return listPoyectos;
    }

    @GetMapping("/clientes/proyectos/contrato")
    public Proyecto findProyectoByNumeroContrato(@RequestParam String numeroContrato){
        Proyecto proyecto = proyectoServicesImp.getProyectoByNumeroContrato(numeroContrato);
        return proyecto;
    }

    @GetMapping("clientes/proyecyos/{id}/documentos")
    public List<String> findDocumentList(@PathVariable Long id){
        List<String> documentos = proyectoServicesImp.getAllDocumentos(id);
        return documentos;
    }

    //METODO POST
    @PostMapping("clientes/{id}/proyectos/nuevoProyecto")
    public String saveProyecto(@RequestBody Proyecto proyecto, @PathVariable Long id){
        
        Boolean validarProyecto = proyectoServicesImp.validarProectoExistente(proyecto);

        if(!validarProyecto){
            Cliente cliente = proyectoServicesImp.registrarProyectoById(proyecto, id);
            List<Proyecto> proyectosCliente = cliente.getProyectosList();
            Proyecto proyectoGuardado = proyectosCliente.get(proyectosCliente.size()-1);
            return "el proyecto se aguardado exitosamente. Se a guardado en el cliente con email: "+proyectoGuardado.getEmailCliente();
        }
        Proyecto proyectoExistente = proyectoServicesImp.getProyectoByNumeroContrato(proyecto.getNumeroContrato());
        return "el numero de contrato que desas guardar ya existe en la base de datos de proyectos. y pertenece al cliente con email: "+proyectoExistente.getEmailCliente();
    }

    //metodo put para agregar documentos al proyecto

    //metodo put para agregar imagenes del antes

    //metodo put para agregar imagenes del despues
}
