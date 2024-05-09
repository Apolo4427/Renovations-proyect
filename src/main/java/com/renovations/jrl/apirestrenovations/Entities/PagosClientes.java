package com.renovations.jrl.apirestrenovations.Entities;

//import java.util.HashMap;
//import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
//import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
public class PagosClientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PagoClienteId;
    private String fecha_pago;
    private String metodo_pago;
    private String valor_pagado;
    private String factura;


    public PagosClientes(Long id, String fecha_pago, String metodo_pago, String valor_pagado, String factura) {
        this.fecha_pago = fecha_pago;
        this.metodo_pago = metodo_pago;
        this.valor_pagado = valor_pagado;
        this.factura = factura;
    }
     
    
}
