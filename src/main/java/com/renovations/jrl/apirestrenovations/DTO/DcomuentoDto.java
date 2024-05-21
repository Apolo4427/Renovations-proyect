package com.renovations.jrl.apirestrenovations.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DcomuentoDto {

    private String name;
    public DcomuentoDto(String name) {
        this.name = name;
    }
    
}
