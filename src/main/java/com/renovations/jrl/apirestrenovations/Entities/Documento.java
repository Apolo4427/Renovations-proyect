package com.renovations.jrl.apirestrenovations.Entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@Table(name = "documentos")
public class Documento {    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @Lob//large object
    private byte[] data;

    public Documento(UUID id, String name, byte[] data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }    
}
