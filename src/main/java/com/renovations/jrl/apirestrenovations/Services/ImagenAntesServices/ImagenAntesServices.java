package com.renovations.jrl.apirestrenovations.Services.ImagenAntesServices;

//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
//import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.Entities.ImagenAntes;

public interface ImagenAntesServices {
    ImagenAntes cargarImagenAntes(MultipartFile imagenAntes, Long ProyectoId)throws IOException;
    List<ImagenAntes> getAllImagenesAntes (Long proyectoId);
    //ImagenAntes getImagenAntes (UUID id, Long proyectoId) throws FileNotFoundException;
}
