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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
//import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@Table(
    uniqueConstraints = @UniqueConstraint(
        name = "Numero de contrato unico",
        columnNames = "numero_contrato"
    )
)
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proyectoId;
    @NotBlank(message = "Se debe indicar el numero del contrato.")
    private String numeroContrato;
    private String fecha_estimado;//cuando iran a ver el proyecto a relizar 
    private String fecha_inicio;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImagenesAntes> imagenesAntes;

    private String contratante;
    @NotBlank(message = "Se debe indicar el correo del cliente.")
    private String emailCliente;
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

    //documentos del proyecto
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Filesproyecto> documentos;

    private String facturas_de_marteriales;

    //entidad compañia aliada
    @OneToMany(
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
    )
    @JoinColumn(
        name = "proyecto_id",
        referencedColumnName = "proyectoId"
    )
    private List<PagosParaAliados> listaDePagosAliados;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImagenesDespues> imagenesDespues;

    public Proyecto(Long proyectoId, String numero_contrato, String fecha_estimado, String fecha_inicio, List<ImagenesAntes> imagenesAntes, String contratante,
            String emailCliente, String valor_aprovado, List<PagosClientes> listaDePagosClientes, String fechaDePago_velorAprovado,
            List<Filesproyecto> documentos, String facturas_de_marteriales, List<PagosParaAliados> lsitaPagosParaAliados, List<ImagenesDespues> imagenesDespues) {
        this.numeroContrato = numero_contrato;
        this.fecha_estimado = fecha_estimado;
        this.fecha_inicio = fecha_inicio;
        this.contratante = contratante;
        this.imagenesAntes = imagenesAntes;
        this.emailCliente = emailCliente;
        this.valor_aprovado = valor_aprovado;
        this.listaDePagosClientes = listaDePagosClientes;
        this.fechaDePago_velorAprovado = fechaDePago_velorAprovado;
        this.documentos = documentos;
        this.facturas_de_marteriales = facturas_de_marteriales;
        this.listaDePagosAliados = lsitaPagosParaAliados;
        this.imagenesDespues = imagenesDespues;
    }

    

       
}
