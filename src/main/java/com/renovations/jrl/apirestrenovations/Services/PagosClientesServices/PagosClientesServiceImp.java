package com.renovations.jrl.apirestrenovations.Services.PagosClientesServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.renovations.jrl.apirestrenovations.Entities.PagosClientes;
import com.renovations.jrl.apirestrenovations.Entities.Proyecto;
import com.renovations.jrl.apirestrenovations.Repositories.ProyectoRepository;

public class PagosClientesServiceImp implements PagosClientesServices {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Override
    public List<PagosClientes> registrarPagoDelCliente(PagosClientes pago, String numeroContrato) {

        Proyecto proyecto = proyectoRepository.findByNumeroContrato(numeroContrato);
        List<PagosClientes> listPagosCliente = proyecto.getListaDePagosClientes();
        listPagosCliente.add(pago);
        proyecto.setListaDePagosClientes(listPagosCliente);
        proyectoRepository.save(proyecto);

        return listPagosCliente;
    }

    @Override
    public List<PagosClientes> actualizarPagoCliente(PagosClientes pago, String numeroContrato, Long id) {
        
        Proyecto proyecto = proyectoRepository.findByNumeroContrato(numeroContrato);
        List<PagosClientes> listPagosClientes = proyecto.getListaDePagosClientes();
        for (PagosClientes pagosClientes : listPagosClientes) {
            if (id==pagosClientes.getPagoClienteId()){
                pagosClientes.setFactura(pago.getFactura());
                pagosClientes.setFecha_pago(pago.getFecha_pago());
                pagosClientes.setMetodo_pago(pago.getMetodo_pago());
                pagosClientes.setValor_pagado(pago.getValor_pagado());
                break;
            }
        }
        proyecto.setListaDePagosClientes(listPagosClientes);
        proyectoRepository.save(proyecto);
        return listPagosClientes;
    }
    
}   
