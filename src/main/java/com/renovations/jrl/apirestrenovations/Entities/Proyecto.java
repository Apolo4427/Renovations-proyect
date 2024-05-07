package com.renovations.jrl.apirestrenovations.Entities;

import jakarta.persistence.Entity;
//import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//@Builder
public class Proyecto {

    private String fecha_inicio;
    //antes arreglo imagenes
    private String contratante;
    private String valor_aprovado;
    //entidad pagos
    private String fechaDePago_velorAprovado;
    private Long presupuesto_aprovado;
    private String documentos;
    private String facturas_de_marteriales;
    //entidad compañia aliada 
    //despues arreglo imagenes
}
