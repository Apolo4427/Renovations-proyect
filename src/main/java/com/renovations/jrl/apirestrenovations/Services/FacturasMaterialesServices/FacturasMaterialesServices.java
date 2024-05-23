package com.renovations.jrl.apirestrenovations.Services.FacturasMaterialesServices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.Entities.FacturasMateriales;

public interface FacturasMaterialesServices {
    FacturasMateriales cargarFacturasMateriales(MultipartFile facturaMaterial, Long proyectoId, String fecha) throws IOException;
     List<FacturasMateriales> getAllFcturas (Long proyectoId);
    FacturasMateriales getFactura (UUID id, Long proyectoId) throws FileNotFoundException;
}
