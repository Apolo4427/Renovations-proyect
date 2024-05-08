package com.renovations.jrl.apirestrenovations.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
//import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//@Builder
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proyectoId;
    private String fecha_inicio;
    //antes arreglo imagenes
    private String contratante;
    private String valor_aprovado;

    @OneToMany(//relacion uno a muchos con los pagos de lso clientes
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
    )
    @JoinColumn(
        name = "proyecto_id",
        referencedColumnName = "proyectoId"
    )
    private List<PagosClientes> listaDePagosClientes;


    private String fechaDePago_velorAprovado;
    private Long presupuesto_aprovado;
    private String documentos;
    private String facturas_de_marteriales;
    //entidad compañia aliada
    //despues arreglo imagenes
}
