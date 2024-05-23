package com.renovations.jrl.apirestrenovations.Controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.renovations.jrl.apirestrenovations.DTO.ImagenDTO;
import com.renovations.jrl.apirestrenovations.Entities.ImagenAntes;
import com.renovations.jrl.apirestrenovations.Services.ImagenAntesServices.ImagenAntesServicesImp;

@RestController
@RequestMapping("/clientes/proyectos")
public class ImagenesAntesController {

    @Autowired
    ImagenAntesServicesImp imagenAntesServicesImp;

    @GetMapping("/{proyectoId}/imagenesAntes")
    public ResponseEntity<List<ImagenDTO>> findAllImagenesAntes(@PathVariable Long proyectoId){
        List<ImagenAntes> lisImagenes = imagenAntesServicesImp.getAllImagenesAntes(proyectoId);
        List<ImagenDTO> listImagenDTOs = lisImagenes.stream().map(imagen->{
          return  new ImagenDTO(imagen.getOriginalName(), Base64.encodeBase64String(imagen.getData()));
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listImagenDTOs);
    }

    @PostMapping("/{proyectoId}/nuevaImagenAntes")
    public ResponseEntity<String> cargarImagenAntes(@RequestParam("imagen") MultipartFile imagen, @PathVariable Long proyectoId) throws IOException{
        ImagenAntes imagenNueva = imagenAntesServicesImp.cargarImagenAntes(imagen, proyectoId);
        return ResponseEntity.ok("Se ha guardado la imagen con exito, con nombre: "+imagenNueva.getOriginalName());
    }

}
