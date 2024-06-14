package com.renovations.jrl.apirestrenovations.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clientes/proyectos")
public class ProyectoController {

    @Autowired 
    ProyectoServicesImp proyectoServicesImp;

    //METODOS GET
    @GetMapping("/{proyectoId}")
    public Proyecto getProyectoById(@PathVariable Long proyectoId){
        return proyectoServicesImp.getProyectoById(proyectoId);
    }

    @GetMapping("/list/{clienteId}")
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
    public ResponseEntity<Map<String, String>> saveProyecto(@RequestBody Proyecto proyecto, @PathVariable Long id){
        
        Boolean validarProyecto = proyectoServicesImp.validarProectoExistente(proyecto);

        Map<String, String> response = new HashMap<>();

        if(!validarProyecto){
            Cliente cliente = proyectoServicesImp.registrarProyectoById(proyecto, id);
            List<Proyecto> proyectosCliente = cliente.getProyectosList();
            Proyecto proyectoGuardado = proyectosCliente.get(proyectosCliente.size()-1);
            response.put("message", "El proyecto se ha guardado exitosamente. Se ha guardado en el cliente con email: " + proyectoGuardado.getEmailCliente());
            return ResponseEntity.ok(response);
        }
        Proyecto proyectoExistente = proyectoServicesImp.getProyectoByNumeroContrato(proyecto.getNumeroContrato());
        response.put("message", "El número de contrato que deseas guardar ya existe en la base de datos de proyectos y pertenece al cliente con email: " + proyectoExistente.getEmailCliente());
        return ResponseEntity.badRequest().body(response);
    }

    //METODO PUT
    @PatchMapping("/{proyectoId}/editarProyecto")
    public ResponseEntity<String> upDateProyecto(@PathVariable Long proyectoId, @RequestBody Proyecto proyecto){
        proyectoServicesImp.actualizarProyecto(proyecto, proyectoId);
        return ResponseEntity.ok("El proyecto se actualizado exitosamente");
    }


}
