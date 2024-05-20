package com.renovations.jrl.apirestrenovations.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.renovations.jrl.apirestrenovations.Entities.Cliente;
import com.renovations.jrl.apirestrenovations.Services.ServiceClienteImpl;
import com.renovations.jrl.apirestrenovations.error.ClienteNoFundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ServiceClienteImpl serviceClienteImpl;

    //METODOS GET
    @GetMapping("/todos")
    public List<Cliente> findAllClientes(){
        return serviceClienteImpl.getAllClientes();
    }

    @GetMapping("/emailsWithOutContrato")
    public List<String> findEmailsWithOutContrato(){
        return serviceClienteImpl.getAllEmailsWtihOutContrato();
    }

    @GetMapping("/emailsWithContrato")
    public List<String> findEmailsWithContrato(){
        return serviceClienteImpl.getAllEamilsWithContrato();
    }

    @GetMapping("/emails")
    public List<String> findAllEmails(){
        return serviceClienteImpl.getAllEmails();
    }

    @GetMapping("/email")
    public Cliente findClientePorEmail(@RequestParam String email) throws ClienteNoFundException{
        return serviceClienteImpl.getClienteByEmail(email);
    }

    @GetMapping("/id")
    public Cliente findClientePorID(@RequestParam Long id) throws ClienteNoFundException{
        return serviceClienteImpl.getClienteBYId(id);
    }

    //METODO POST
    @PostMapping("/registrar")
    public Cliente saveCliente(@Valid @RequestBody Cliente cliente){
        return serviceClienteImpl.registrarCliente(cliente);
    }

    //METODO PUT
    @PutMapping("/actualizar/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) throws ClienteNoFundException{
        return serviceClienteImpl.actualizarCliente(id, cliente);
    }

    //METODO DELETE
    @DeleteMapping("/eliminar/{id})")
    public String deleteCliente(@PathVariable Long id){
        return serviceClienteImpl.eliminarCliente(id);
    }
}
