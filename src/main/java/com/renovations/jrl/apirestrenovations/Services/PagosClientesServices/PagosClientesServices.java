package com.renovations.jrl.apirestrenovations.Services.PagosClientesServices;

import java.util.List;

import com.renovations.jrl.apirestrenovations.Entities.PagosClientes;

public interface PagosClientesServices {
    List<PagosClientes> getAllPagosCliente(Long id);
    List<PagosClientes> registrarPagoDelCliente(PagosClientes pago, Long id);
    List<PagosClientes> actualizarPagoCliente(PagosClientes pago, String numeroContrato, Long id);
}
