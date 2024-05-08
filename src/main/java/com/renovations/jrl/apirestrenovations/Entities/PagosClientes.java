package com.renovations.jrl.apirestrenovations.Entities;

//import java.util.HashMap;
//import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//@Builder
public class PagosClientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PagoClienteId;
    private String fecha_pago;
    private String metodo_pago;
    private String valor_pagado;
    private String factura;
    private String fecha_estimado;//cuando iran a ver el proyecto a relizar 

    
}
