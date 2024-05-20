package com.renovations.jrl.apirestrenovations.Services.DocumentosServices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.Entities.Documento;

public interface DocumentosServices {

    //permite almacenar o cargar archivos a la base de datos 
    Documento cargarDocumento(MultipartFile docmuento, Long id)throws IOException;
    List<Documento> getAllDocumentos (Long proyectoId);
    Documento getDocumento (UUID id, Long proyectoId) throws FileNotFoundException;
}
