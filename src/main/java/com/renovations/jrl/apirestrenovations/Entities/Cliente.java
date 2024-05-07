package com.renovations.jrl.apirestrenovations.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;

// import java.util.UUID;

// import org.hibernate.annotations.ColumnTransformer;
// import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;
    private String contacto;
    @Column(
        nullable = false//indicamos que el email no puede ser nulo
    )
    @Email(message = "El valor debe ser un correo electrónico válido.")
    @NotBlank(message = "Recuerda ingresar un valor de email.")
    private String email;
    private String referido_por;


    @OneToMany(
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
    )
    @JoinColumn(//columna on la que se hara referencia
        name="Cliente_id",
        referencedColumnName= "id"
    )
    private List<Proyecto> proyectosList;


    public Cliente(Long id, String nombre, String direccion, String contacto, String email, String referido_por,
            List<Proyecto> proyectosList) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.email = email;
        this.referido_por = referido_por;
    }

    
}
