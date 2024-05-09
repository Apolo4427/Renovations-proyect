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
    private String compañiaAliada;
    private Long valorPagado;
    private String fechaDePago;
    private String facturasDePagos;


    public PagosParaAliados(Long id, String compañiaAliada, Long valorPagado, String fechaDePago, String facturasDePagos) {
        this.compañiaAliada = compañiaAliada;
        this.valorPagado = valorPagado;
        this.fechaDePago = fechaDePago;
        this.facturasDePagos = facturasDePagos;
    }

}
