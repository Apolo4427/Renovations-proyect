package com.renovations.jrl.apirestrenovations.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentoDto {

    private String id;
    private String name;
    public DocumentoDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public DocumentoDto(){}
    
}
