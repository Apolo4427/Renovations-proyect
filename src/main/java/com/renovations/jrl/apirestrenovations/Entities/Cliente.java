package com.renovations.jrl.apirestrenovations.Entities;

import java.util.UUID;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@Table(
    uniqueConstraints = @UniqueConstraint(//indicamos que el email debe ser unico y no se puede repetir
        name = "email_unico",//damos nombre a este constructor unico
        columnNames = "email"//indivamos el nombre de la columna que no puee tener valores repetidos
    )
)
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String nombre;
    private String direccion;
    private String contacto;
    @Column(
        nullable = false//indicamos que el email no puede ser nulo

    )
    private String email;
    private String referido_por;
    private String fecha_estimado;


    public Cliente(UUID id, String nombre, String direccion, String contacto, String email, String referido_por,
            String fecha_estimado) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.email = email;
        this.referido_por = referido_por;
        this.fecha_estimado = fecha_estimado;
    }

    
}
