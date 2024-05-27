package com.renovations.jrl.apirestrenovations.DTO;

import java.io.Serializable;


public class ImagenDTO implements Serializable {
    private String name;
    private String base64Data;

    public ImagenDTO(String name, String base64Data) {
        this.name = name;
        this.base64Data = base64Data;
    }

    public ImagenDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }
}
