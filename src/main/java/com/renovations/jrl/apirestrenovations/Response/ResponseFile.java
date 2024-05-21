package com.renovations.jrl.apirestrenovations.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private Long size;
    
    public ResponseFile(String name, String url, String type, Long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }
}
