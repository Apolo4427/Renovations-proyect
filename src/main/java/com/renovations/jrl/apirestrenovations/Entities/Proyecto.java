package com.renovations.jrl.apirestrenovations.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
//import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proyectoId;
    private String fecha_estimado;//cuando iran a ver el proyecto a relizar 
    private String fecha_inicio;
    //antes arreglo imagenes (coleccion de String)
    private String contratante;
    private String valor_aprovado;

    @OneToMany(//relacion uno a muchos con los pagos de los clientes
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

    public Proyecto(Long proyectoId, String fecha_estimado, String fecha_inicio, String contratante,
            String valor_aprovado, List<PagosClientes> listaDePagosClientes, String fechaDePago_velorAprovado,
            Long presupuesto_aprovado, String documentos, String facturas_de_marteriales) {
        this.fecha_estimado = fecha_estimado;
        this.fecha_inicio = fecha_inicio;
        this.contratante = contratante;
        this.valor_aprovado = valor_aprovado;
        this.listaDePagosClientes = listaDePagosClientes;
        this.fechaDePago_velorAprovado = fechaDePago_velorAprovado;
        this.presupuesto_aprovado = presupuesto_aprovado;
        this.documentos = documentos;
        this.facturas_de_marteriales = facturas_de_marteriales;

    }

    
    //entidad compañia aliada
    //despues arreglo imagenes

       
}
