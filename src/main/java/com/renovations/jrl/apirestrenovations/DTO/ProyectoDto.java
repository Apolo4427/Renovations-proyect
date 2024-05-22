package com.renovations.jrl.apirestrenovations.DTO;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProyectoDto {

    private Long id;
    private String numeroContrato;
    private String fecha_estimado;
    private String fecha_inicio;
    private String contratante;
    private String emailCliente;
    private String valor_aprovado;
    private List<Long> listaDePagosClientes;
    private String fechaDePago_velorAprovado;
    private List<String> documentos;
    private String facturas_de_marteriales;
    private List<Long> listaDePagosAliados;

    public ProyectoDto(Long id, String numeroContrato, String fecha_estimado, String fecha_inicio, String contratante,
            String emailCliente, String valor_aprovado, List<Long> listaDePagosClientes,
            String fechaDePago_velorAprovado, List<String> documentos, String facturas_de_marteriales,
            List<Long> listaDePagosAliados) {
        this.id = id;
        this.numeroContrato = numeroContrato;
        this.fecha_estimado = fecha_estimado;
        this.fecha_inicio = fecha_inicio;
        this.contratante = contratante;
        this.emailCliente = emailCliente;
        this.valor_aprovado = valor_aprovado;
        this.listaDePagosClientes = listaDePagosClientes;
        this.fechaDePago_velorAprovado = fechaDePago_velorAprovado;
        this.documentos = documentos;
        this.facturas_de_marteriales = facturas_de_marteriales;
        this.listaDePagosAliados = listaDePagosAliados;
    }

    public ProyectoDto(){}

}
