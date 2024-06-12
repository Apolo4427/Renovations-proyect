package com.renovations.jrl.apirestrenovations.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.renovations.jrl.apirestrenovations.Entities.PagosClientes;
import com.renovations.jrl.apirestrenovations.Services.PagosClientesServices.PagosClientesServiceImp;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clientes/proyectos")
public class PagosClientesController {

    @Autowired
    PagosClientesServiceImp pagosClientesServiceImp;

    //METODO GET
    @GetMapping("/{id}/pagosClientes")
    public List<PagosClientes> findAllPagosCliente(@PathVariable Long id){
        List<PagosClientes> pagosCliente = pagosClientesServiceImp.getAllPagosCliente(id);
        return pagosCliente;
    }

    //METODO POST
    @PostMapping("/{id}/pagosClientes/nuevoPago")
    public List<PagosClientes> savePagoCliente(@PathVariable Long id, @RequestBody PagosClientes pago){
        return pagosClientesServiceImp.registrarPagoDelCliente(pago, id);
    }

    //METODO PUT
    @PutMapping("/id/pagosclientes/detalle")
    public List<PagosClientes> updatePago(@RequestBody PagosClientes pago, @RequestParam String numeroContrato, @RequestParam Long id){
        return pagosClientesServiceImp.actualizarPagoCliente(pago, numeroContrato, id);
    }

    //metodo para agregar facturas
}
