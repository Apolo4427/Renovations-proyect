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

import com.renovations.jrl.apirestrenovations.Entities.PagosParaAliados;
import com.renovations.jrl.apirestrenovations.Services.PagosParaAliadosServices.PagosParaAliadosServicesImp;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clientes/proyectos")
public class PagosAliadosController {

    @Autowired
    PagosParaAliadosServicesImp pagosParaAliadosServicesImp;

    @GetMapping("/{id}/pagosAliados")
    public List<PagosParaAliados> findAllPagosAliados(@PathVariable Long id){
        return pagosParaAliadosServicesImp.getAllPagosAliado(id);
    }

    @PostMapping("{id}/registrarPago")
    public List<PagosParaAliados> savePagoAliado(@PathVariable Long id, @RequestBody PagosParaAliados pago){
        return pagosParaAliadosServicesImp.registrarPagoDelAliado(pago, id);
    }

    @PutMapping("/id/pagosAliados/detalle")
    public List<PagosParaAliados> updatePago(@RequestBody PagosParaAliados pago, @RequestParam String numeroContrato, @RequestParam Long id){
        return pagosParaAliadosServicesImp.actualizarPagoAliado(pago, numeroContrato, id);
    }

    //metodo para agregar facturas

}
