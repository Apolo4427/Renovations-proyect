package com.renovations.jrl.apirestrenovations.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
public class PagosParaAliados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PagoParaAliadoId;
    private String empresaAliada;
    private String valorPagado;
    private String fechaDePago;
    private String facturasDePagos;


    public PagosParaAliados(Long id, String compañiaAliada, String valorPagado, String fechaDePago, String facturasDePagos) {
        this.empresaAliada = compañiaAliada;
        this.valorPagado = valorPagado;
        this.fechaDePago = fechaDePago;
        this.facturasDePagos = facturasDePagos;
    }

}
