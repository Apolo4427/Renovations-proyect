package com.renovations.jrl.apirestrenovations.Services.ImagenDespuesServices;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.Entities.ImagenDespues;

public interface ImagenDespuesServices {
    ImagenDespues cargarImagenDespues(MultipartFile imagenDespues, Long ProyectoId)throws IOException;
    List<ImagenDespues> getAllImagenesDespues (Long proyectoId);
    //ImagenAntes getImagenAntes (UUID id, Long proyectoId) throws FileNotFoundException;
}
