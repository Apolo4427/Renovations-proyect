package com.renovations.jrl.apirestrenovations.Services.FilesServices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.Entities.Filesproyecto;
import com.renovations.jrl.apirestrenovations.Response.ResponseFile;

public interface FilesServices {
    //Permite almacenar o cargar archivos a la base de datos
    Filesproyecto almacenarFile(MultipartFile file) throws IOException;
    //Permite descargar archivos de la base de datos
    Optional<Filesproyecto> getFile(UUID id) throws FileNotFoundException;
    //Permite consultar una lista de archivos
    //se creo un objeto plano paradar un formato a los archivos (informacion que se desea mostrar al cargar el archivo en una web por ejemplo) -> ResponseFile
    List<ResponseFile> getAllFiles();

}
