package com.renovations.jrl.apirestrenovations.Services.PagosParaAliadosServices;

import java.util.List;

import com.renovations.jrl.apirestrenovations.Entities.PagosParaAliados;

public interface PagosParaAliadosServices {
    List<PagosParaAliados> getAllPagosAliado(Long id);
    List<PagosParaAliados> registrarPagoDelAliado(PagosParaAliados pago, Long id);
    List<PagosParaAliados> actualizarPagoAliado(PagosParaAliados pago, String numeroContrato, Long id);
}
