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
@Table(name = "imagenes_antes")
@Builder
public class ImagenesAntes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String originalName;
    private String type;
    @Lob
    private byte[] data;

    public ImagenesAntes(UUID id, String originalName, String type, byte[] data) {
        this.id = id;
        this.originalName = originalName;
        this.type = type;
        this.data = data;
    }
}
