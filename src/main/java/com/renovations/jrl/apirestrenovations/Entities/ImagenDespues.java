package com.renovations.jrl.apirestrenovations.Entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
public class ImagenDespues {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String originalName;
    @Lob
    private byte[] data;
    
    public ImagenDespues(UUID id, String originalName, byte[] data) {
        this.id = id;
        this.originalName = originalName;
        this.data = data;
    }
}
