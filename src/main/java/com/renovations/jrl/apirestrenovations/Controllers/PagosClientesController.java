package com.renovations.jrl.apirestrenovations.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    //METODO POST
    @PostMapping("/cliente/proyectos/{id}/pagosClientes/nuevoPago")
    public List<PagosClientes> savePagoCliente(@PathVariable Long id, @RequestBody PagosClientes pago){
        return pagosClientesServiceImp.registrarPagoDelCliente(pago, id);
    }

    //METODO PUT
    @PutMapping("/Cliente/proyectos/id/pagosclientes/detalle")
    public List<PagosClientes> updatePago(@RequestBody PagosClientes pago, @RequestParam String numeroContrato, @RequestParam Long id){
        return pagosClientesServiceImp.actualizarPagoCliente(pago, numeroContrato, id);
    }

    //metodo para agregar facturas
}
