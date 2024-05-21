package com.renovations.jrl.apirestrenovations.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DcomuentoDto {

    private String name;
    private String url;
    public DcomuentoDto(String name, String url) {
        this.name = name;
        this.url = url;
    }
    
}
