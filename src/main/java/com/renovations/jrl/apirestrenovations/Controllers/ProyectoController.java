package com.renovations.jrl.apirestrenovations.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Services.ProyectoServices.ProyectoServicesImp;

@RestController
@RequestMapping("/clientes/proyectos")
public class ProyectoController {

    @Autowired 
    ProyectoServicesImp proyectoServicesImp;

    //METODOS GET
    @GetMapping("/id/{proyectoId}")
    public Proyecto getProyectoById(@PathVariable Long proyectoId){
        return proyectoServicesImp.getProyectoById(proyectoId);
    }

    @GetMapping("/{clienteId}")
    public List<Proyecto> findAllProyectosById(@PathVariable Long clienteId){
        List<Proyecto> listProyectos = proyectoServicesImp.getAllProyectosClientesById(clienteId);
        return listProyectos;
    }

    @GetMapping("/email")
    public List<Proyecto> findAllProyectosByEmail(@RequestParam String email){
        List<Proyecto> listPoyectos = proyectoServicesImp.getAllProyectosClienteByEmail(email);
        return listPoyectos;
    }

    @GetMapping("/contrato")
    public Proyecto findProyectoByNumeroContrato(@RequestParam String numeroContrato){
        Proyecto proyecto = proyectoServicesImp.getProyectoByNumeroContrato(numeroContrato);
        return proyecto;
    }
    
    //METODO POST
    @PostMapping("/{id}/nuevoProyecto")
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

    //metodo put para agregar imagenes del antes


}
