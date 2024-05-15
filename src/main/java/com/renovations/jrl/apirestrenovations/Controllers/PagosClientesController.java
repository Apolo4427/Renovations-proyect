package com.renovations.jrl.apirestrenovations.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.renovations.jrl.apirestrenovations.Entities.PagosClientes;
import com.renovations.jrl.apirestrenovations.Services.PagosClientesServices.PagosClientesServiceImp;

@RestController
public class PagosClientesController {

    @Autowired
    PagosClientesServiceImp pagosClientesServiceImp;

    //METODO GET
    @GetMapping("/clientes/proyectos/{id}/pagosClientes")
    public List<PagosClientes> findAllPagosCliente(@PathVariable Long id){
        List<PagosClientes> pagosCliente = pagosClientesServiceImp.getAllPagosCliente(id);
        return pagosCliente;
    }
}
