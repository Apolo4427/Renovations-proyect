package com.renovations.jrl.apirestrenovations.Services.PagosParaAliadosServices;

import java.util.List;

import com.renovations.jrl.apirestrenovations.Entities.PagosParaAliados;

public interface PagosParaAliadosServices {
    List<PagosParaAliados> getAllPagosCliente(Long id);
    List<PagosParaAliados> registrarPagoDelCliente(PagosParaAliados pago, Long id);
    List<PagosParaAliados> actualizarPagoCliente(PagosParaAliados pago, String numeroContrato, Long id);
}
